package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 04.06.2015.
 */

import net.compitek.javakit.database.domain.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository("UserDao")
public class UserDao extends AbstractDao<Long,User>{
    private static final Logger log = Logger.getLogger(UserDao.class);

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public Boolean isLoginFree(String login){
        TypedQuery<Boolean> query = entityManager.createQuery(" SELECT count(*)=0 from  User u where u.login like :login", Boolean.class);
        query.setParameter("login",login);
        Boolean response = query.getSingleResult();
        return response;
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public User findByLogin(String login) {

        TypedQuery<User> query = entityManager.createQuery("SELECT u from User u where u.login like :login ",User.class);
        query.setParameter("login",login);
        try {
            User user =  query.getSingleResult();
            return user;
        }
        catch (NoResultException e) {
            log.info("findByLogin:NoResultException",e);
            return (User)null;
          }
    }

}
