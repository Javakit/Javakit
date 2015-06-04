package net.compitek.javakit.database.domain;

import java.io.Serializable;

/**
 * Created by Evgene on 04.06.2015.
 */
public interface IPersistenceEntity<ID extends Serializable> {
    public ID getId();
}
