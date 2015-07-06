package net.compitek.javakit.web.controller.admin;/**
 * Created by Evgene on 05.07.2015.
 */

import net.compitek.javakit.database.domain.INamedPersistenceEntity;
import net.compitek.javakit.database.domain.NamedPersistenceEntity;
import net.compitek.javakit.database.domain.News;
import net.compitek.javakit.service.NewsService;
import net.compitek.javakit.web.formBean.NewsListBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/NewsEditor")
public class NewsEditorController {
    private static final Logger log = Logger.getLogger(NewsEditorController.class);

    @Autowired
    NewsService newsService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private LocaleResolver localeResolver;

    @Secured("ROLE_EditNews")
    @RequestMapping("/newsList")
    public String newsList(Map<String, Object> map) {

        List<News> newsList =  (List<News>)newsService.getEntityList(News.class);

        NewsListBean bean = new NewsListBean();
        bean.setNewsList(newsList);
        map.put("newsListBean", bean);

        return "/admin/newsList";
    }

    @Secured("ROLE_EditNews")
    @RequestMapping("/edit/{id}")
    public String editNews(@PathVariable("id") long id, Map<String, Object> map, HttpServletRequest request) {
        if (id > 0)
            map.put("entity", newsService.findById(id));
        else
            map.put("entity", new News());
        map.put("pageTitle", messageSource.getMessage("pageTitles_/NewsEditor/edit", null, localeResolver.resolveLocale(request)));
        return "/admin/newsEdit";
    }

    @Secured("ROLE_EditNews")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNews(HttpServletRequest request, @ModelAttribute(value = "entity") News news, Map<String, Object> map) {

        if (request.getParameterMap().containsKey("save")) {
            if (news.getId() != null)
                newsService.update(news);
            else
                newsService.create(news);
        }
        return "redirect:/NewsEditor/newsList";
    }

    @Secured("ROLE_EditNews")
    @RequestMapping(value = "/delete/{id}")
    public String deleteNews(@PathVariable("id") long id, @ModelAttribute(value = "entity") News news, Map<String, Object> map) {
        newsService.delete(id);
        return "redirect:/NewsEditor/newsList";
    }
}

