package uz.lesson.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import uz.lesson.payload.ApiResponse;
import uz.lesson.payload.ReqLogin;
import uz.lesson.payload.ReqUser;
import uz.lesson.security.JwtProvider;
import uz.lesson.service.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public HttpEntity<?> register(@Valid @RequestBody ReqUser reqUser) {
        ApiResponse apiResponse = authService.register(reqUser);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT)
                .body(apiResponse);
    }

    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin reqLogin) {
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    reqLogin.getUsername(),
                    reqLogin.getPassword()
            ));
            String generateToken = jwtProvider.generateToken(reqLogin.getUsername());
            return ResponseEntity.ok(generateToken);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
