package net.compitek.javakit.security;/**
 * Created by Evgene on 19.06.2015.
 */

import net.compitek.javakit.database.domain.Permission;
import net.compitek.javakit.database.domain.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private static final Logger log = Logger.getLogger(UserDetailsImpl.class);
    private List<Permission> permissionList;
    private User user;
    public UserDetailsImpl() {
        super();
    }
    public UserDetailsImpl(User user) {
        super();
        this.user = user;
    }
    public void setAuthorities(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
    public List<Permission> getAuthorities() {
        return permissionList;
    }
    public String getPassword() {
        return user.getPassword();
    }
    public String getUsername() {
        return user.getLogin();
    }
    public boolean isAccountNonExpired() {
        return true;
    }
    public boolean isAccountNonLocked() {
        return true;
    }
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public boolean isEnabled() {
        return true;
    }
}
