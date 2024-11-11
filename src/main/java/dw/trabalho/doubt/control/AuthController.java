package dw.trabalho.doubt.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dw.trabalho.doubt.control.dto.UserDto;
import dw.trabalho.doubt.model.User;
import dw.trabalho.doubt.security.JwtUtil;
import dw.trabalho.doubt.service.AuthService;

@RestController
// @CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            UserDto userDto = new UserDto(); // Cria um UserDto com os dados do user
            String token = authService.login(user, userDto);
            String role = jwtUtil.extractRole(token); // Extrai a role do token
            Thread.sleep(1000);
            return ResponseEntity.ok(new AuthResponse(token, role, userDto)); // Retorna token e role

        } catch (Exception e) {
            System.out.println(e);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return ResponseEntity.badRequest().body("Invalid username or password");
        }

    }

}
