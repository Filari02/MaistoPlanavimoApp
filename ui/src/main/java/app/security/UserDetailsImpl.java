package app.security;

import app.model.Asmuo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@AllArgsConstructor
@Getter
public class UserDetailsImpl implements UserDetails {
    private int id;
    private String username;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    @JsonIgnore
    private String password;

    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private static final String SIMPLE_USER_ROLE = "ROLE_USER";

    public static UserDetailsImpl of(Asmuo user) {
        return new UserDetailsImpl(
                Math.toIntExact(user.getId()),
                user.getVardas(),
                user.getElPastas(),
                new ArrayList<>(),
                user.getSlaptazodis());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
