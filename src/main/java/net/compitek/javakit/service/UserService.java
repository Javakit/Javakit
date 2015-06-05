package net.compitek.javakit.service;/**
 * Created by Evgene on 05.06.2015.
 */

import net.compitek.javakit.database.dao.UserDao;
import net.compitek.javakit.database.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractEntityService<Long,User, UserDao> {
    private static final Logger log = Logger.getLogger(UserService.class);

    @Autowired
    UserDao userDao;

    @Override
    protected UserDao getDao() {
        return userDao;
    }

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}
