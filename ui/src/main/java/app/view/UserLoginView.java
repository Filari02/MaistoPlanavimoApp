package app.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginView {
    private String email;
    private String password;

}