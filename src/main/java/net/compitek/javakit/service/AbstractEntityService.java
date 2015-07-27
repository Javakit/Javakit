package net.compitek.javakit.service;

import net.compitek.javakit.database.dao.AbstractDao;
import net.compitek.javakit.database.domain.IPersistenceEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.io.Serializable;
import java.util.List;

/**
 * Created by Evgene on 05.06.2015.
 */
public abstract class AbstractEntityService<ID extends Serializable, PEntity extends IPersistenceEntity<ID>, Dao extends AbstractDao<ID,PEntity>> {

    protected abstract Dao getDao();
    protected abstract Class<PEntity>   getEntityClass();

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public List<? extends PEntity> getEntityList(Class clazz){
        return getDao().getEntityList(clazz);
    }

    @Transactional
    public void create(PEntity entity){
        getDao().create(entity);
    }

    @Transactional
    public void update(PEntity entity) {
        getDao().update(entity);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void createOrUpdate(PEntity entity) {
        boolean isNumber = entity.getId().getClass().equals(Long.class) || entity.getId().getClass().equals(Integer.class);
        if (entity.getId()!=null && (!isNumber || (Long)entity.getId()>0)){
            getDao().update(entity);
        }
        else
        {
            getDao().create(entity);
        }
    }


    @Transactional
    public void delete(ID id) {
        getDao().delete(id, getEntityClass());
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PEntity findById(ID id){
        return getDao().findById(id, getEntityClass());
    }

    public List<PEntity>  getReferencesByIds(List<Long> ids){
        return getDao().getReferencesByIds(ids, getEntityClass());
    }

}
