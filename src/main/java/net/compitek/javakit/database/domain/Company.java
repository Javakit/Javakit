package net.compitek.javakit.database.domain;/**
 * Created by Evgene on 04.06.2015.
 */

import org.apache.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="company")
public class Company extends NamedPersistenceEntity {
    private static final Logger log = Logger.getLogger(Company.class);

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "company",
            cascade = CascadeType.ALL
    )
    private List<User> userList = new ArrayList<User>();

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
