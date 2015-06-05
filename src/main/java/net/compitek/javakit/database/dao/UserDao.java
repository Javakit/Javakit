package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 04.06.2015.
 */

import net.compitek.javakit.database.domain.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("UserDao")
public class UserDao extends AbstractDao<Long,User>{
    private static final Logger log = Logger.getLogger(UserDao.class);
}
