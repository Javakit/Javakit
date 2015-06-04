package net.compitek.javakit.database.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by Evgene on 04.06.2015.
 */
@MappedSuperclass
public abstract class NamedPersistenceEntity extends PersistenceEntity implements INamedPersistenceEntity<Long> {

    @Column(name = "name",nullable = false)
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedPersistenceEntity(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public NamedPersistenceEntity(String name) {
        super();
        this.name = name;
    }

    public NamedPersistenceEntity() {
        super();
    }
}
