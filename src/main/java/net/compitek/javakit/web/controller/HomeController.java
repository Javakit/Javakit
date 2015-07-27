package net.compitek.javakit.web.controller;

import net.compitek.javakit.database.domain.News;
import net.compitek.javakit.security.UserDetailsImpl;
import net.compitek.javakit.service.NewsService;
import net.compitek.javakit.utils.locale.MessageSourceWrapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
public class HomeController {

    private static final Logger log = Logger.getLogger(HomeController.class);


    @Autowired
    @Qualifier("messageSource")
    private MessageSourceWrapper messageSource;

    @Autowired
    private NewsService newsService;

    @RequestMapping("/hello")
    public String hello(HttpServletRequest request,ModelMap modelMap){
        //map.put("message","hello!");
        try {
            Object details = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            modelMap.put("userDetails", (UserDetailsImpl) details);
        }
        catch (Exception e){
            log.info("AUTHENTICATED_ANONYMOUSLY");
        }

        return "hello";
    }



    @RequestMapping("/")
    public String home(Map map){
        map.put("newsList", newsService.getEntityList(News.class));
        return "home";
    }


}
