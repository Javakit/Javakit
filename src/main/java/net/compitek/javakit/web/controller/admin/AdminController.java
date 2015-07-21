package net.compitek.javakit.web.controller.admin;/**
 * Created by Evgene on 05.07.2015.
 */

import net.compitek.javakit.database.domain.*;
import net.compitek.javakit.service.*;
import net.compitek.javakit.utils.StringUtils;
import net.compitek.javakit.web.formBean.NamedEntityListBean;
import net.compitek.javakit.web.formBean.SelectedRoleBean;
import net.compitek.javakit.web.formBean.UserFormBean;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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

    private String namedEntityList(@RequestParam String entityName, Map<String, Object> map) {
        List<NamedPersistenceEntity> namedEntityList = namedPersistenceEntityService.getNamedEntityList(entityName);

        NamedEntityListBean bean = new NamedEntityListBean();
        bean.setEntityList(namedEntityList);
        bean.setEntityName(entityName);
        map.put("entityListBean", bean);

        return "/component/EntityList";
    }

    @Secured("ROLE_EditCompany")
    @RequestMapping("/Company/entityList")
    public String companyList(Map<String, Object> map) {
        return namedEntityList("Company", map);
    }

    @Secured("ROLE_EditUser")
    @RequestMapping("/User/entityList")
    public String userList(Map<String, Object> map) {
        return namedEntityList("User", map);
    }

    @Secured("ROLE_EditRole")
    @RequestMapping("/Role/entityList")
    public String roleList(Map<String, Object> map) {
        return namedEntityList("Role", map);
    }

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private LocaleResolver localeResolver;
    @Autowired
    private CompanyService companyService;

    @Secured("ROLE_EditCompany")
    @RequestMapping("/Company/edit/{id}")
    public String editCompany(@PathVariable("id") long id, Map<String, Object> map, HttpServletRequest request) {
        if (id > 0)
            map.put("company", companyService.findById(id));
        else
            map.put("company", new Company());
        map.put("pageTitle", messageSource.getMessage("pageTitles_/admin/Company/edit", null, localeResolver.resolveLocale(request)));
        return "/admin/editCompany";
    }

    @Secured("ROLE_EditCompany")
    @RequestMapping(value = "/Company/save", method = RequestMethod.POST)
    public String saveCompany(HttpServletRequest request, @Valid @ModelAttribute(value = "company") Company company, Map<String, Object> map) {
        if (request.getParameterMap().containsKey("cancel"))
            return "redirect:/admin/Company/entityList";
        if (company.getId() != null)
            companyService.update(company);
        else
            companyService.create(company);
        return namedEntityList("Company", map);
    }

    @Secured("ROLE_EditCompany")
    @RequestMapping("/Company/delete/{id}")
    public String deleteCompany(@PathVariable("id") long id, @ModelAttribute("entityListBean") NamedEntityListBean bean, Map<String, Object> map) {
        companyService.delete(id);
        return "redirect:/admin/Company/entityList";
    }

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @Secured("ROLE_EditRole")
    @RequestMapping("/Role/edit/{id}")
    public String editRole(@PathVariable("id") long id, Map<String, Object> map, HttpServletRequest request, @ModelAttribute("selectedRoleBean") SelectedRoleBean selectedRoleBean) {
        Role role;
        if (id > 0)
            role = roleService.findById(id);
        else
            role = new Role();
        Hibernate.initialize(role.getPermissionList());
        selectedRoleBean.setRole(role);
        List<Long> permissionIdsList = new ArrayList<Long>();
        for (Permission permission : role.getPermissionList()) {
            permissionIdsList.add(permission.getId());
        }
        selectedRoleBean.setPermissionIdsList(permissionIdsList);
        map.put("selectedRoleBean", selectedRoleBean);
        map.put("fullPermissionList", namedPersistenceEntityService.getNamedEntityList(Permission.class.getName()));
        map.put("pageTitle", messageSource.getMessage("pageTitles_/admin/Role/edit", null, localeResolver.resolveLocale(request)));
        return "/admin/editRole";
    }

    @Secured("ROLE_EditRole")
    @RequestMapping(value = "/Role/save", method = RequestMethod.POST)
    public String saveRole(HttpServletRequest request, Map<String, Object> map,
                           @Valid @ModelAttribute("selectedRoleBean") SelectedRoleBean selectedRoleBean, BindingResult result) {
        if (request.getParameterMap().containsKey("cancel"))
            return "redirect:/admin/Role/entityList";
        Role role = selectedRoleBean.getRole();
        if (result.hasErrors()) {
            map.put("fullPermissionList", namedPersistenceEntityService.getNamedEntityList(Permission.class.getName()));
            return "/admin/editRole";
        }
        List<Permission> permissionList = permissionService.getReferencesByIds(selectedRoleBean.getPermissionIdsList());
        role.setPermissionList(permissionList);
        if (role.getId() != null)
            roleService.update(role);
        else
            roleService.create(role);
        return namedEntityList("Role", map);
    }

    @Secured("ROLE_EditRole")
    @RequestMapping("/Role/delete/{id}")
    public String deleteRole(@PathVariable("id") long id, @ModelAttribute("entityListBean") NamedEntityListBean bean, Map<String, Object> map) {
        roleService.delete(id);
        return "redirect:/admin/Role/entityList";
    }

    @Autowired
    private UserService userService;

    @Secured("ROLE_EditUser")
    @RequestMapping("/User/edit/{id}")
    public String editUser(@PathVariable("id") long id, Map<String, Object> map, HttpServletRequest request, @ModelAttribute("userFormBean") UserFormBean userFormBean) {
        User user;
        if (id > 0)
            user = userService.findById(id);
        else
            user = new User();

        List<Long> roleIdsList = new ArrayList<Long>();
        for (Role role : user.getRoleList()) {
            roleIdsList.add(role.getId());
        }

        userFormBean.setUser(user);
        userFormBean.setRoleIdsList(roleIdsList);

        map.put("userFormBean", userFormBean);
        map.put("fullRoleList", namedPersistenceEntityService.getNamedEntityList(Role.class.getName()));
        map.put("companyList", namedPersistenceEntityService.getNamedEntityList(Company.class.getName()));

        map.put("userFormAction", "/admin/User/save");
        map.put("pageTitle", messageSource.getMessage("pageTitles_/admin/User/edit", null, localeResolver.resolveLocale(request)));
        map.put("hidePassword", true);

        return "/admin/editUser";
    }


    @Secured("ROLE_EditUser")
    @RequestMapping(value = "/User/save", method = RequestMethod.POST)
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveUser(HttpServletRequest request, Map<String, Object> map,
                           @ModelAttribute("userFormBean") @Valid UserFormBean userFormBean, BindingResult result) {

        if (request.getParameterMap().containsKey("cancel"))
            return "redirect:/admin/User/entityList";

        User bakingUser = userFormBean.getUser();
        User user;
        if (bakingUser.getId()!=null && bakingUser.getId() > 0) {
            user = userService.findById(bakingUser.getId());
        } else {
            user = new User();
            String generatedPassword = StringUtils.generatePassword(6);
            user.setPassword(generatedPassword);
            userFormBean.setRepeatedPassword(generatedPassword);
        }

        if (
                (
                        (bakingUser.getId()!=null && !(bakingUser.getId() > 0)) || !bakingUser.getLogin().equals(user.getLogin())
                )
                    && !userService.isLoginFree(bakingUser.getLogin())
            ) {
            result.addError(new FieldError("userFormBean", "user.login",
                messageSource.getMessage("UnUniq.userFormBean.user.login", null, localeResolver.resolveLocale(request))));
        }
        if (result.hasErrors()) {
            map.put("fullRoleList", namedPersistenceEntityService.getNamedEntityList(Role.class.getName()));
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

        return namedEntityList("User", map);
    }

    @Secured("ROLE_EditUser")
    @RequestMapping("/User/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Map<String, Object> map) {
        userService.delete(id);
        return "redirect:/admin/User/entityList";
    }

}
