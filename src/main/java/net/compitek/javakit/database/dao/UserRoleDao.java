package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 04.06.2015.
 */

import net.compitek.javakit.database.domain.UserRole;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("UserRoleDao")
public class UserRoleDao  extends AbstractDao<Long,UserRole> {
    private static final Logger log = Logger.getLogger(UserRoleDao.class);
}
