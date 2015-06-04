package net.compitek.javakit.database.domain;/**
 * Created by Evgene on 04.06.2015.
 */

import org.apache.log4j.Logger;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="user_role")
public class UserRole extends PersistenceEntity {
    private static final Logger log = Logger.getLogger(UserRole.class);

    @ManyToOne
    @JoinColumn(name = "roleid")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
