package net.compitek.javakit.web.formBean;/**
 * Created by Evgene on 05.07.2015.
 */

import net.compitek.javakit.database.domain.News;
import org.apache.log4j.Logger;

import java.util.List;

public class NewsListBean {
    private static final Logger log = Logger.getLogger(NewsListBean.class);

    private List<News> newsList;


    public List<News> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }
}
