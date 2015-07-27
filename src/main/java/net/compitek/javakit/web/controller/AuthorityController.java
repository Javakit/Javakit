package net.compitek.javakit.web.controller;

import net.compitek.javakit.database.domain.User;
import net.compitek.javakit.service.UserService;
import net.compitek.javakit.utils.locale.MessageSourceWrapper;
import net.compitek.javakit.web.formBean.UserFormBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthorityController {
    private static final Logger log = Logger.getLogger(AuthorityController.class);

    @Autowired
    @Qualifier("messageSource")
    private MessageSourceWrapper messageSource;


    @Autowired
    private UserService userService;

    @RequestMapping("/isLoginFree")// ajax �� jquery validation plugin
    @ResponseBody
    public String checkIsFree(@RequestParam String login4test) {
        return userService.isLoginFree(login4test) ? "true" : "false";
    }


    @RequestMapping("/signUp")
    public String createNewAccount(ModelMap modelMap) {
        UserFormBean userFormBean = new UserFormBean();
        userFormBean.setUser(new User());
        modelMap.put("userFormBean", userFormBean);
        modelMap.put("userFormAction", "/auth/saveUser");
        return "auth/signup";
    }


    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(
            HttpServletRequest request,
            ModelMap modelMap,
            @Valid @ModelAttribute UserFormBean userFormBean,
            BindingResult result,
            @RequestParam(required = false,value = "cancel") String isCancel
    ) {
        if (isCancel!=null) {
            modelMap.put("message",
                    messageSource.getMessage("ActionCanceled", null, request.getLocale()));
            return "/home";
        }
        if (!userService.isLoginFree(userFormBean.getUser().getLogin())) {
            FieldError error = new FieldError("userFormBean", "user.login",
                    messageSource.getMessage("UnUniq.userFormBean.user.login"));
            result.addError(error);
        }
        if (!userFormBean.getUser().getPassword().equals(userFormBean.getRepeatedPassword())) {
            result.addError(new FieldError("userFormBean", "repeatedPassword",
                    messageSource.getMessage("Size.userFormBean.repeatedPassword")));
        }
        if (result.hasErrors()) {
            return "/auth/signup";
        }
        if (userFormBean.getUser().getPassword().equals(userFormBean.getRepeatedPassword())) {
            userService.create(userFormBean.getUser());
            return "/auth/welcome";
        } else {
            modelMap.put("message",
                    messageSource.getMessage("message.RegistrationError", null, request.getLocale()));
            return "/auth/signup";
        }
    }


    @RequestMapping("/login")
    public String login() {
        return "auth/login";
    }

    @RequestMapping("/error403")
    public String error403() {
        return "auth/error403";
    }

}
