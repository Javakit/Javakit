package net.compitek.javakit.database.domain;

import java.io.Serializable;

/**
 * Created by Evgene on 04.06.2015.
 */
public interface INamedPersistenceEntity<ID extends Serializable> extends IPersistenceEntity<ID> {

    public String getName();

}
