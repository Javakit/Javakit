package net.compitek.javakit.database.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by Evgene on 04.06.2015.
 */
@MappedSuperclass
public abstract class PersistenceEntity implements IPersistenceEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
