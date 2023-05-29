package app.view;

import app.security.UserDetailsImpl;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserInfo {
    private int id;
    private String name;
    private String email;
    private List<String> roles;

    public static UserInfo of(UserDetailsImpl user, List<String> roles) {
        return UserInfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getUsername())
                .roles(roles)
                .build();
    }
}
