package net.compitek.javakit.web.formBean;/**
 * Created by Evgene on 17.07.2015.
 */

import net.compitek.javakit.database.domain.Role;
import org.apache.log4j.Logger;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SelectedRoleBean {
    private static final Logger log = Logger.getLogger(SelectedRoleBean.class);

    @Valid
    @NotNull
    Role role;

    List<Long> permissionIdsList;
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public List<Long> getPermissionIdsList() {
        return permissionIdsList;
    }
    public void setPermissionIdsList(List<Long> permissionIdsList) {
        this.permissionIdsList = permissionIdsList;
    }
}
