package net.compitek.javakit.service;/**
 * Created by Evgene on 17.07.2015.
 */

import net.compitek.javakit.database.dao.NamedPersistenceEntityDao;
import net.compitek.javakit.database.domain.NamedPersistenceEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamedPersistenceEntityService {
    private static final Logger log = Logger.getLogger(NamedPersistenceEntityService.class);

    @Autowired
    private NamedPersistenceEntityDao namedPersistantEntityDao;

    public List<NamedPersistenceEntity> getNamedEntityList(String classname){
        return namedPersistantEntityDao.getNamedEntityList(classname);
    }
}
