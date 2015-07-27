package net.compitek.javakit.web.controller.admin;/**
 * Created by Evgene on 05.07.2015.
 */

import net.compitek.javakit.database.domain.*;
import net.compitek.javakit.service.*;
import net.compitek.javakit.utils.PersistanceEntityUtils;
import net.compitek.javakit.utils.StringUtils;
import net.compitek.javakit.utils.locale.LocaleHolder;
import net.compitek.javakit.utils.locale.MessageSourceWrapper;
import net.compitek.javakit.web.formBean.NamedEntityListBean;
import net.compitek.javakit.web.formBean.SelectedRoleBean;
import net.compitek.javakit.web.formBean.UserFormBean;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.AutowireCandidateQualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = Logger.getLogger(AdminController.class);

    @Secured({"ROLE_EditCompany", "ROLE_EditUser", "ROLE_EditUser", "ROLE_EditSelfUser", "ROLE_EditRole", "ROLE_EditNews"})
    @RequestMapping("")
    public String adminHome() {
        return "/admin/home";
    }

    @Autowired
    private NamedPersistenceEntityService namedPersistenceEntityService;

    @Autowired
    @Qualifier("messageSource")
    private MessageSourceWrapper messageSource;

    @Autowired
    private PersistanceEntityUtils persistanceEntityUtils;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    private String namedEntityList(@RequestParam String entityName,ModelMap modelMap) {
        List<NamedPersistenceEntity> namedEntityList = namedPersistenceEntityService.getNamedEntityList(entityName);

        NamedEntityListBean bean = new NamedEntityListBean();
        bean.setEntityList(namedEntityList);
        bean.setEntityName(entityName);
        modelMap.put("entityListBean", bean);

        return "/component/EntityList";
    }

    @Secured("ROLE_EditCompany")
    @RequestMapping("/Company/entityList")
    public String companyList(ModelMap modelMap) {
        return namedEntityList("Company", modelMap);
    }

    @Secured("ROLE_EditUser")
    @RequestMapping("/User/entityList")
    public String userList(ModelMap modelMap) {
        return namedEntityList("User",modelMap);
    }

    @Secured("ROLE_EditRole")
    @RequestMapping("/Role/entityList")
    public String roleList(ModelMap modelMap) {
        return namedEntityList("Role", modelMap);
    }


    @Secured("ROLE_EditCompany")
    @RequestMapping("/Company/edit/{id}")
    public String editCompany(@PathVariable("id") long id, ModelMap modelMap, Locale locale) {
        modelMap.put("company", id > 0 ? companyService.findById(id) : companyService.findById(id));
        modelMap.put("pageTitle", messageSource.getMessage("pageTitles_/admin/Company/edit"));
        return "/admin/editCompany";
    }

    @Secured("ROLE_EditCompany")
    @RequestMapping(value = "/Company/save", method = RequestMethod.POST)
    public String saveCompany(HttpServletRequest request,
                              @Valid @ModelAttribute(value = "company") Company company,
                              ModelMap modelMap,
                              @RequestParam(required = false, value = "cancel") String isCancel
    ) {
        if (isCancel != null)
            return "redirect:/admin/Company/entityList";
        if (company.getId() != null)
            companyService.update(company);
        else
            companyService.create(company);
        return namedEntityList("Company", modelMap);
    }

    @Secured("ROLE_EditCompany")
    @RequestMapping("/Company/delete/{id}")
    public String deleteCompany(@PathVariable("id") long id, @ModelAttribute("entityListBean") NamedEntityListBean bean, ModelMap modelMap) {
        companyService.delete(id);
        return "redirect:/admin/Company/entityList";
    }

    @Secured("ROLE_EditRole")
    @RequestMapping("/Role/edit/{id}")
    public String editRole(@PathVariable("id") long id,
                           ModelMap modelMap,
                           HttpServletRequest request,
                           @ModelAttribute("selectedRoleBean") SelectedRoleBean selectedRoleBean) {
        Role role;
        if (id > 0)
            role = roleService.findById(id);
        else
            role = new Role();
        //Hibernate.initialize(role.getPermissionList());
        selectedRoleBean.setRole(role);
        List<Long> permissionIdsList = persistanceEntityUtils.toListLong(role.getPermissionList());

        selectedRoleBean.setPermissionIdsList(permissionIdsList);
        modelMap.put("selectedRoleBean", selectedRoleBean);
        modelMap.put("fullPermissionList", namedPersistenceEntityService.getNamedEntityList(Permission.class.getName()));
        modelMap.put("pageTitle", messageSource.getMessage("pageTitles_/admin/Role/edit"));
        return "/admin/editRole";
    }

    @Secured("ROLE_EditRole")
    @RequestMapping(value = "/Role/save", method = RequestMethod.POST)
    public String saveRole(HttpServletRequest request,
                           ModelMap modelMap,
                           @Valid @ModelAttribute("selectedRoleBean") SelectedRoleBean selectedRoleBean,
                           BindingResult result,
                           @RequestParam(required = false, value = "cancel") String isCancel
    ) {
        if (isCancel != null)
            return "redirect:/admin/Role/entityList";
        Role role = selectedRoleBean.getRole();
        if (result.hasErrors()) {
            modelMap.put("fullPermissionList", namedPersistenceEntityService.getNamedEntityList(Permission.class.getName()));
            return "/admin/editRole";
        }
        List<Permission> permissionList = permissionService.getReferencesByIds(selectedRoleBean.getPermissionIdsList());
        role.setPermissionList(permissionList);
        if (role.getId() != null)
            roleService.update(role);
        else
            roleService.create(role);
        return namedEntityList("Role", modelMap);
    }

    @Secured("ROLE_EditRole")
    @RequestMapping("/Role/delete/{id}")
    public String deleteRole(@PathVariable("id") long id, @ModelAttribute("entityListBean") NamedEntityListBean bean) {
        roleService.delete(id);
        return "redirect:/admin/Role/entityList";
    }

    @Secured("ROLE_EditUser")
    @RequestMapping("/User/edit/{id}")
    public String editUser(
            @PathVariable("id") long id,
            ModelMap modelMap,
            HttpServletRequest request,
            @ModelAttribute("userFormBean") UserFormBean userFormBean
    ) {
        User user;
        if (id > 0)
            user = userService.findById(id);
        else
            user = new User();

        List<Long> roleIdsList = persistanceEntityUtils.toListLong(user.getRoleList());

        userFormBean.setUser(user);
        userFormBean.setRoleIdsList(roleIdsList);

        modelMap.put("userFormBean", userFormBean);
        modelMap.put("fullRoleList", namedPersistenceEntityService.getNamedEntityList(Role.class.getName()));
        modelMap.put("companyList", namedPersistenceEntityService.getNamedEntityList(Company.class.getName()));

        modelMap.put("userFormAction", "/admin/User/save");
        modelMap.put("pageTitle", messageSource.getMessage("pageTitles_/admin/User/edit"));
        modelMap.put("hidePassword", true);

        return "/admin/editUser";
    }

    @Secured("ROLE_EditUser")
    @RequestMapping(value = "/User/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveUser(HttpServletRequest request,
                           ModelMap modelMap,
                           @ModelAttribute("userFormBean") @Valid UserFormBean userFormBean,
                           BindingResult result,
                           @RequestParam(required = false, value = "cancel") String isCancel
    ) {
        if (isCancel != null)
            return "redirect:/admin/User/entityList";

        User bakingUser = userFormBean.getUser();
        User user;
        if (bakingUser.getId() != null && bakingUser.getId() > 0) {
            user = userService.findById(bakingUser.getId());
        } else {
            user = new User();
            String generatedPassword = StringUtils.generatePassword(6);
            user.setPassword(generatedPassword);
            userFormBean.setRepeatedPassword(generatedPassword);
        }

        boolean isExistedUser = (bakingUser.getId() != null && bakingUser.getId() > 0);
        boolean isLoginValid = isExistedUser && bakingUser.getLogin().equals(user.getLogin())
                || userService.isLoginFree(bakingUser.getLogin());

        if (!isLoginValid) {
            result.addError(new FieldError("userFormBean", "user.login",
                    messageSource.getMessage("UnUniq.userFormBean.user.login")));
        }
        if (result.hasErrors()) {
            modelMap.put("fullRoleList", namedPersistenceEntityService.getNamedEntityList(Role.class.getName()));
            return "/admin/editRole";
        }

        user.setCompany(bakingUser.getCompany());
        user.setName(bakingUser.getName());
        user.setLogin(bakingUser.getLogin());
        user.setEmail(bakingUser.getEmail());

        List<Role> roleList = roleService.getReferencesByIds(userFormBean.getRoleIdsList());

        user.setRoleList(roleList);

        if (user.getId() != null)
            userService.update(user);
        else
            userService.create(user);

        return namedEntityList("User", modelMap);
    }

    @Secured("ROLE_EditUser")
    @RequestMapping("/User/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/User/entityList";
    }

}
