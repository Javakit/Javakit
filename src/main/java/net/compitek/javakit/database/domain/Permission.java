package net.compitek.javakit.database.domain;/**
 * Created by Evgene on 04.06.2015.
 */

import org.apache.log4j.Logger;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class Permission extends NamedPersistenceEntity {
    private static final Logger log = Logger.getLogger(Permission.class);

}
