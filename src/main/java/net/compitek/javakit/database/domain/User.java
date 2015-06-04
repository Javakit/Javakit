package net.compitek.javakit.database.domain;/**
 * Created by Evgene on 04.06.2015.
 */

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name="user")
public class User extends NamedPersistenceEntity {
    private static final Logger log = Logger.getLogger(User.class);

    @ManyToOne
    @JoinColumn(name = "companytypeid")
    private Company company;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "user"
    )
    private List<UserRole> UserRoleList = new ArrayList<UserRole>();

    public List<UserRole> getUserRoleList() {
        return UserRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        UserRoleList = userRoleList;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
