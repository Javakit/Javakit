package net.compitek.javakit.web.controller;/**
 * Created by Evgene on 19.06.2015.
 */

import net.compitek.javakit.database.domain.*;
import net.compitek.javakit.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class PremakeController {
    private static final Logger log = Logger.getLogger(PremakeController.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private NewsService newsService;

    @RequestMapping("/preMake")
    @Secured("IS_AUTHENTICATED_ANONYMOUSLY")
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public String preMakeDB(ModelMap modelMap) {

        if (!userService.isLoginFree("test")) {
            modelMap.put("message", "user 'test' already existed");
        } else {
            try {

                Company company = new Company();
                company.setName("test company");
                companyService.create(company);

                User user = new User();
                user.setName("Иванов И.И.");
                user.setCompany(company);
                user.setLogin("test");
                user.setPassword("12345");
                user.setEmail("123@gmail.com");
                userService.create(user);

                Role role1 = new Role();
                role1.setName("administrator");
                roleService.create(role1);

                Role role2 = new Role();
                role2.setName("news editor");
                roleService.create(role2);


                List<Role> roleList = new ArrayList<Role>();

                roleList.add(role1);
                roleList.add(role2);
                user.setRoleList(roleList);

                userService.update(user);

                Permission permissionEditCompany = new Permission();
                permissionEditCompany.setName("ROLE_EditCompany");
                permissionService.create(permissionEditCompany);
                Permission permissionEditUser = new Permission();
                permissionEditUser.setName("ROLE_EditUser");
                permissionService.create(permissionEditUser);
                Permission permissionEditSelfUser = new Permission();
                permissionEditSelfUser.setName("ROLE_EditSelfUser");
                permissionService.create(permissionEditSelfUser);
                Permission permissionEditRole = new Permission();
                permissionEditRole.setName("ROLE_EditRole");
                permissionService.create(permissionEditRole);

                List<Permission> permissionList1 = new ArrayList<Permission>();
                permissionList1.add(permissionEditCompany);
                permissionList1.add(permissionEditUser);
                permissionList1.add(permissionEditRole);

                role1.setPermissionList(permissionList1);
                roleService.update(role1);

                Permission permissionEditNews = new Permission();
                permissionEditNews.setName("ROLE_EditNews");
                permissionService.create(permissionEditNews);

                List<Permission> permissionList2 = new ArrayList<Permission>();
                permissionList2.add(permissionEditNews);

                role2.setPermissionList(permissionList2);
                roleService.update(role2);

                modelMap.put("message", "preMake finished");

            } catch (Exception e) {
                log.error(e);
            }

        }
        return "home";
    }


}
