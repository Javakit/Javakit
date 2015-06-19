package net.compitek.javakit.database.domain;/**
 * Created by Evgene on 04.06.2015.
 */

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="permission")
public class Permission extends NamedPersistenceEntity implements GrantedAuthority {
    private static final Logger log = Logger.getLogger(Permission.class);

    public String getAuthority() {
        return getName();
    }
}
