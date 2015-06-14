package net.compitek.javakit.database.dao;

import net.compitek.javakit.database.domain.IPersistenceEntity;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Created by Evgene on 04.06.2015.
 */
public abstract class AbstractDao<ID extends Serializable, PEntity extends IPersistenceEntity<ID>> {

    private static final Logger log = Logger.getLogger(AbstractDao.class);

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public boolean create(PEntity entity) {
        try {
            entityManager.persist(entity);
            return true;
        }
        catch (Exception e)
        {
            log.error("AbstractDao.create:",e);
            return false;
        }
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void  update(PEntity entity){
        entityManager.merge(entity);
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void  delete(ID id, Class clazz){
        PEntity entity = (PEntity) entityManager.getReference(clazz, id);
        entityManager.remove(entity);
    }
    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PEntity findById(ID id, Class clazz){
        return (PEntity) entityManager.find(clazz, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


}
