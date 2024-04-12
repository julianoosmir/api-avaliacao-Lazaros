package avaliacao.acesso.controllers;

import avaliacao.acesso.config.jwt.JwtRequest;
import avaliacao.acesso.entity.Usuario;
import avaliacao.acesso.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest request) {
        return userService.signin(request);

    }
}