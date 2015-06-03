package net.compitek.javakit.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class SimpleController {

    private static final Logger log = Logger.getLogger(SimpleController.class);

    @RequestMapping(value={"/","/hello"})
    public String Hello(HttpServletRequest request){
        return "hello";
    }

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @RequestMapping("/i18n")
    public String i18n(Map<String, Object> map,HttpServletRequest request){
        map.put("DeleteConfirmMessage",messageSource.getMessage("DeleteConfirm",null,localeResolver.resolveLocale(request)));
        return "i18n";
    }
}
