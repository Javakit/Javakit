package net.compitek.javakit.web.controller.admin;/**
 * Created by Evgene on 05.07.2015.
 */

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private static final Logger log = Logger.getLogger(AdminController.class);

    @Secured({"ROLE_EditCompany", "ROLE_EditUser", "ROLE_EditUser", "ROLE_EditSelfUser", "ROLE_EditRole", "ROLE_EditNews"})
    @RequestMapping("")
    public String adminHome() {
        return "/admin/home";
    }

}
