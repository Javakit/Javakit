package net.compitek.javakit.web.formBean;/**
 * Created by Evgene on 17.07.2015.
 */

import net.compitek.javakit.database.domain.NamedPersistenceEntity;
import org.apache.log4j.Logger;

import java.util.List;

public class NamedEntityListBean {
    private static final Logger log = Logger.getLogger(NamedEntityListBean.class);

    private List<NamedPersistenceEntity> entityList;

    private String entityName;

    public List<NamedPersistenceEntity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<NamedPersistenceEntity> entityList) {
        this.entityList = entityList;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
