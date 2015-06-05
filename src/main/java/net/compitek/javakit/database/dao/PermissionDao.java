package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 04.06.2015.
 */

import net.compitek.javakit.database.domain.Permission;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("PermissionDao")
public class PermissionDao extends AbstractDao<Long,Permission>  {
    private static final Logger log = Logger.getLogger(PermissionDao.class);
}
