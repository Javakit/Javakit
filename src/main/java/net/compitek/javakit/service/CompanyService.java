package net.compitek.javakit.service;/**
 * Created by Evgene on 05.06.2015.
 */

import net.compitek.javakit.database.dao.CompanyDao;
import net.compitek.javakit.database.domain.Company;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService extends AbstractEntityService<Long,Company,CompanyDao>{
    private static final Logger log = Logger.getLogger(CompanyService.class);

    @Autowired
    CompanyDao companyDao;

    @Override
    protected CompanyDao getDao() {
        return companyDao;
    }

    @Override
    protected Class<Company> getEntityClass() {
        return Company.class;
    }
}
