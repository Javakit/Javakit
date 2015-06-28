package net.compitek.javakit.service;/**
 * Created by Evgene on 05.06.2015.
 */

import net.compitek.javakit.database.dao.RoleDao;
import net.compitek.javakit.database.domain.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends AbstractEntityService<Long, Role, RoleDao> {
    private static final Logger log = Logger.getLogger(RoleService.class);

    @Autowired
    RoleDao roleDao;

    @Override
    protected RoleDao getDao() {
        return roleDao;
    }

    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }
}
