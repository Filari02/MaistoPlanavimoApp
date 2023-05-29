package app.controller;

import app.serivce.AsmuoService;
import app.view.UserInfo;
import app.view.UserLoginView;
import app.view.UserRegisterView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AsmuoController {
    private final AsmuoService asmuoService;

    @PostMapping("/register")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@RequestBody UserRegisterView userRegisterView) {
        asmuoService.register(userRegisterView);
    }

    @PostMapping("/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<UserInfo> login(@RequestBody UserLoginView userLoginView) {
            return asmuoService.login(userLoginView);
    }

    @PostMapping("/logout")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<?> logout() {
        return asmuoService.logout();
    }

}
