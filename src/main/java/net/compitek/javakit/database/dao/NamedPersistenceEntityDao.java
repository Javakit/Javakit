package net.compitek.javakit.database.dao;/**
 * Created by Evgene on 17.07.2015.
 */

import net.compitek.javakit.database.domain.NamedPersistenceEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class NamedPersistenceEntityDao {
    private static final Logger log = Logger.getLogger(NamedPersistenceEntityDao.class);

    @PersistenceContext
    protected EntityManager entityManager;

    public List<NamedPersistenceEntity> getNamedEntityList(String classname)throws RuntimeException{
        return entityManager.createQuery(" select new net.compitek.javakit.database.domain.NamedPersistenceEntity(c.id, c.name)  " +
                " from " + classname + " c ").getResultList();
    }
}
