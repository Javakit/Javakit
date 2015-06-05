package net.compitek.javakit.service;/**
 * Created by Evgene on 05.06.2015.
 */


import net.compitek.javakit.database.dao.UserRoleDao;
import net.compitek.javakit.database.domain.UserRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends AbstractEntityService<Long,UserRole,UserRoleDao> {
    private static final Logger log = Logger.getLogger(UserRoleService.class);

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    protected UserRoleDao getDao() {
        return userRoleDao;
    }

    @Override
    protected Class<UserRole> getEntityClass() {
        return UserRole.class;
    }
}
