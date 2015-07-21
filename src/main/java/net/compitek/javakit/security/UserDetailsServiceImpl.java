package net.compitek.javakit.security;/**
 * Created by Evgene on 19.06.2015.
 */

import net.compitek.javakit.database.dao.UserDao;
import net.compitek.javakit.database.domain.Permission;
import net.compitek.javakit.database.domain.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = Logger.getLogger(UserDetailsServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDao.findByLogin(login);
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        TypedQuery<Permission> query = entityManager.createQuery(
                "select p from User u join u.roleList r join r.permissionList p where u.id like :userId", Permission.class);
        query.setParameter("userId", user.getId());
        List<Permission> list = query.getResultList();
        if (!list.isEmpty())
            userDetails.setAuthorities(list);
        return userDetails;
    }
}
