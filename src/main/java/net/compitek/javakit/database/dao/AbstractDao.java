package net.compitek.javakit.database.dao;

import net.compitek.javakit.database.domain.IPersistenceEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Evgene on 04.06.2015.
 */
public abstract class AbstractDao<ID extends Serializable, PEntity extends IPersistenceEntity<ID>> {

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
            System.out.println("DefaultDao.create error:" + e.getMessage());
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
