package net.compitek.javakit.service;/**
 * Created by Evgene on 05.06.2015.
 */

import net.compitek.javakit.database.dao.NewsDao;
import net.compitek.javakit.database.domain.News;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsService extends AbstractEntityService<Long, News, NewsDao> {
    private static final Logger log = Logger.getLogger(NewsService.class);

    @Autowired
    NewsDao newsDao;

    @Override
    protected NewsDao getDao() {
        return newsDao;
    }

    @Override
    protected Class<News> getEntityClass() {
        return News.class;
    }
}
