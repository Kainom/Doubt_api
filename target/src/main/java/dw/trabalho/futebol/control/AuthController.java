package dw.trabalho.futebol.control;

import dw.trabalho.futebol.model.Adm;
import dw.trabalho.futebol.model.Jogador;
import dw.trabalho.futebol.security.JwtUtil;
import dw.trabalho.futebol.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// @CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Jogador jogador) {
        try {
            String token = authService.login(jogador);
            String role = jwtUtil.extractRole(token); // Extrai a role do token
            return ResponseEntity.ok(new AuthResponse(token, role)); // Retorna token e role

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

    }

    @PostMapping("/adm/login")
    public ResponseEntity<?> loginAdm(@RequestBody Adm adm) {
        try {
            String token = authService.loginAdm(adm);
            String role = jwtUtil.extractRole(token); // Extrai a role do token
            return ResponseEntity.ok(new AuthResponse(token, role)); // Retorna token e role
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

    }
}
