package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 04.06.2015.
 */

import net.compitek.javakit.database.domain.News;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;


@Repository("NewsDao")
public class NewsDao extends AbstractDao<Long,News>{
    private static final Logger log = Logger.getLogger(NewsDao.class);
}
