package app.serivce;

import app.model.Asmuo;
import app.repository.AsmuoRepository;
import app.security.JwtUtils;
import app.security.UserDetailsImpl;
import app.view.ReceptasView;
import app.view.UserInfo;
import app.view.UserLoginView;
import app.view.UserRegisterView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsmuoService {
    private final AsmuoRepository asmuoRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    public List<ReceptasView> getAsmensReceptai(Long asmuoId) {
        Asmuo asmuo = asmuoRepository.findById(asmuoId).get();
        return asmuo.getReceptai().stream().map(ReceptasView::of).collect(Collectors.toList());
    }

    public void register(UserRegisterView userRegisterView) {
        checkEmail((userRegisterView.getEmail()));

        Asmuo asmuo = new Asmuo();
        asmuo.setElPastas(userRegisterView.getEmail());
        asmuo.setVardas(userRegisterView.getName());
        asmuo.setSlaptazodis(encoder.encode(userRegisterView.getPassword()));
        asmuoRepository.save(asmuo);
    }

    public ResponseEntity<UserInfo> login(UserLoginView userLoginView) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginView.getEmail(), userLoginView.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
        List<String> roles = new ArrayList<>();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(UserInfo.of(userDetails, roles));
    }

    private void checkEmail(String email) {
        if(asmuoRepository.findByElPastas(email) != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "That email already exists");
        }
    }

    public ResponseEntity<UserInfo> logout() {
        ResponseCookie responseCookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).build();
    }

}
