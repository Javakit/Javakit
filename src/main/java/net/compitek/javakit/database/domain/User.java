package net.compitek.javakit.database.domain;/**
 * Created by Evgene on 04.06.2015.
 */

import org.apache.log4j.Logger;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="user")
public class User extends NamedPersistenceEntity {
    private static final Logger log = Logger.getLogger(User.class);

    @ManyToOne
    @JoinColumn(name = "companyId")
    private Company company;

    @ManyToMany(
            cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
            name="user_role",
            joinColumns = @JoinColumn( name="roleid"),
            inverseJoinColumns = @JoinColumn( name="permissionid")
    )
    private List<Role> roleList = new ArrayList<Role>();



    @Size(min = 1, max = 255)
    @Column(name = "login", length = 255 )
    private String login;

    @Size(min = 5,max = 255)
    @Column(name = "password", length = 255 )
    private String password;

    @NotNull
    @Size(max = 255)
    @Pattern(regexp = "^.+@.+\\..+$" )
    @Column(name = "email", nullable = false )
    private String email;


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
