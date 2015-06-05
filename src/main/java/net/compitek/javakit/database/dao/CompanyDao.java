package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 04.06.2015.
 */

import net.compitek.javakit.database.domain.Company;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("CompanyDao")
public class CompanyDao extends AbstractDao<Long,Company>{
    private static final Logger log = Logger.getLogger(CompanyDao.class);
}
