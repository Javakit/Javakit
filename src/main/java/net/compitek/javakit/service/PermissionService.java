package net.compitek.javakit.service;/**
 * Created by Evgene on 05.06.2015.
 */

import net.compitek.javakit.database.dao.PermissionDao;
import net.compitek.javakit.database.domain.Permission;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class PermissionService extends AbstractEntityService<Long, Permission, PermissionDao>  {
    private static final Logger log = Logger.getLogger(PermissionService.class);

    @Autowired
    PermissionDao permissionDao;

    @Override
    protected PermissionDao getDao() {
        return permissionDao;
    }

    @Override
    protected Class<Permission> getEntityClass() {
        return Permission.class;
    }
}
