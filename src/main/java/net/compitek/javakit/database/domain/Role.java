package net.compitek.javakit.database.domain;/**
 * Created by Evgene on 04.06.2015.
 */

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="role")
public class Role extends NamedPersistenceEntity {
    private static final Logger log = Logger.getLogger(Role.class);

    @ManyToMany( mappedBy = "roleList",cascade = CascadeType.ALL )
    private List<User> userList;

    @ManyToMany(
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="role_permission",
            joinColumns = @JoinColumn( name="permissionid"),
            inverseJoinColumns = @JoinColumn( name="roleid")
    )
    private List<Permission> permissionList = new ArrayList<Permission>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
