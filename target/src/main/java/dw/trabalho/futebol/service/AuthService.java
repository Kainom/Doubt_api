package dw.trabalho.futebol.service;


import dw.trabalho.futebol.model.Adm;
import dw.trabalho.futebol.model.Jogador;
import dw.trabalho.futebol.repository.AdmRepository;
import dw.trabalho.futebol.repository.JogadorRepository;
import dw.trabalho.futebol.security.JwtUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private AdmRepository admRepository;

    public String registerJogador(Jogador jogador) {
        jogador.setPassword(passwordEncoder.encode(jogador.getPassword()));
        // Save jogador in repository
        return "Jogador registrado com sucesso!";
    }

    public String registerAdm(Adm adm) {
        adm.setPassword(passwordEncoder.encode(adm.getPassword()));
        admRepository.save(adm);
        
        return "Adm registrado com sucesso!";
    }

    public String login(Jogador jogador) {
        // Logic to authenticate jogador and generate JWT token
        Jogador user = jogadorRepository.findByEmail(jogador.getEmail()).getFirst();

        if(user == null){
            throw new RuntimeException("Jogador not found");
        }

        if(!passwordEncoder.matches(jogador.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        String role = jogador.getRole();

        return jwtUtil.generateToken(jogador.getEmail(),role);
    }

    public String loginAdm(Adm adm) {
        // Logic to authenticate adm and generate JWT token
        Adm user = admRepository.findByEmail(adm.getEmail());

        if(user == null){
            throw new RuntimeException("Adm not found");
        }

        System.out.println(passwordEncoder.encode(adm.getPassword()));
        if(!passwordEncoder.matches(adm.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid password");
        }
        String role = adm.getRole();
        return jwtUtil.generateToken(adm.getEmail(),role);
    }
}
