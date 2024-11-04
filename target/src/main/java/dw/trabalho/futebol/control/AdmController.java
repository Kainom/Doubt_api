package dw.trabalho.futebol.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.trabalho.futebol.model.Adm;
import dw.trabalho.futebol.service.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/adm")
public class AdmController {

    @Autowired
    AuthService authService;
      @PostMapping("/")
    public ResponseEntity<String> register(@RequestBody Adm adm) {
        return ResponseEntity.ok(authService.registerAdm(adm));
    }
    
}
