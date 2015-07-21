package net.compitek.javakit.database.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

/**
 * Created by Evgene on 04.06.2015.
 */
@MappedSuperclass
public  class NamedPersistenceEntity extends PersistenceEntity implements INamedPersistenceEntity<Long> {

    @Size(min = 1, max = 255)
    @Column(name = "name",nullable =false, length = 255 )
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
