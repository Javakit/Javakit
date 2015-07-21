package net.compitek.javakit.web.formBean;/**
 * Created by Evgene on 08.06.2015.
 */



import net.compitek.javakit.database.domain.User;
import org.apache.log4j.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserFormBean {
    private static final Logger log = Logger.getLogger(UserFormBean.class);

    @NotNull
    @Valid
    private User user;

    @Size(max = 255)
    private String repeatedPassword;

    private List<Long> roleIdsList;


    public List<Long> getRoleIdsList() {
        return roleIdsList;
    }

    public void setRoleIdsList(List<Long> roleIdsList) {
        this.roleIdsList = roleIdsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
