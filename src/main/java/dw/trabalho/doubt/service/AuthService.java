package dw.trabalho.doubt.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dw.trabalho.doubt.model.User;
import dw.trabalho.doubt.repository.UserRepository;
import dw.trabalho.doubt.security.JwtUtil;


@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;





    public String   login(User userPar) {
        User user = userRepository.findByEmail(userPar.getEmail());

        if(user == null){
            throw new RuntimeException("user not found");
        }
        System.out.println("Hello World");
        if(!passwordEncoder.matches(userPar.getPassword(), user.getPassword())){
            System.out.println(user.getPassword());
            throw new RuntimeException("Invalid password");
        }

        String role = user.getRole();

        return jwtUtil.generateToken(user.getEmail(),role);
    }

     public String encoder(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return "Jogador registrado com sucesso!";
    }


    
}
