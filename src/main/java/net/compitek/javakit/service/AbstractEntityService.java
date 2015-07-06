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

    @Transactional
    public void delete(ID id) {
        getDao().delete(id, getEntityClass());
    }

    @Transactional(readOnly = true, propagation = Propagation.NEVER)
    public PEntity findById(ID id){
        return getDao().findById(id, getEntityClass());
    }



}
