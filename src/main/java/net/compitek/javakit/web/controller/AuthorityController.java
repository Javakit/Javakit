package net.compitek.javakit.web.controller;


import net.compitek.javakit.database.domain.User;
import net.compitek.javakit.service.UserService;
import net.compitek.javakit.web.formBean.UserFormBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthorityController {
    private static final Logger log = Logger.getLogger(AuthorityController.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    @RequestMapping("/isLoginFree")// ajax �� jquery validation plugin
    @ResponseBody
    public String checkIsFree(@RequestParam String login4test){
        return userService.isLoginFree(login4test)?"true":"false";
    }


    @RequestMapping("/signUp")
    public String createNewAccount(Map<String, Object> map){
        UserFormBean userFormBean = new UserFormBean();
        userFormBean.setUser(new User());
        map.put("userFormBean", userFormBean);
        map.put("userFormAction","/auth/saveUser");
        return "auth/signup";
    }



    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public String saveUser(HttpServletRequest request, Map<String, Object> map, @Valid @ModelAttribute UserFormBean userFormBean,BindingResult result){
        if (request.getParameterMap().containsKey("cancel"))
        {
            map.put("message",messageSource.getMessage("ActionCanceled",null,request.getLocale()));
            return "/home";
        }
        if(userFormBean.getUser().getLogin().length()==0){
            result.addError(new FieldError("userFormBean","user.login",messageSource.getMessage("Size.userFormBean.user.login",null, RequestContextUtils.getLocale(request))));
        }
        else if(!userService.isLoginFree(userFormBean.getUser().getLogin()))
        {
            result.addError(new FieldError("userFormBean","user.login",messageSource.getMessage("UnUniq.userFormBean.user.login",null, RequestContextUtils.getLocale(request))));
        }
        if(!userFormBean.getUser().getPassword().equals(userFormBean.getRepeatedPassword()) )
        {
            result.addError(new FieldError("userFormBean","repeatedPassword",messageSource.getMessage("Size.userFormBean.repeatedPassword",null,RequestContextUtils.getLocale(request))));
        }
        if (result.hasErrors())
        {
            return "/auth/signup";
        }
        if(userFormBean.getUser().getPassword().equals(userFormBean.getRepeatedPassword()) )
        {
            userService.create(userFormBean.getUser());
            return "/auth/welcome";
        }
        else
        {
            map.put("message",messageSource.getMessage("message.RegistrationError",null,request.getLocale()));
            return "/auth/signup";
        }
    }

    @RequestMapping("/login")
    public String login() { return "auth/login"; }

    @RequestMapping("/error403")
    public String error403() { return "auth/error403"; }

}
