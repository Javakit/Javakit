package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 04.06.2015.
 */

import net.compitek.javakit.database.domain.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("RoleDao")
public class RoleDao extends AbstractDao<Long,Role> {
    private static final Logger log = Logger.getLogger(RoleDao.class);
}
