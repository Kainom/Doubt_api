package dw.trabalho.futebol.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "jogador", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cod_jogador;
    
    @Column(nullable = false, length = 60)
    private String nome;
    
    @Column(nullable = false, length = 60)
    private String email;
    
    @Column(nullable = false)
    private LocalDate datanasc;

    private String password;


    private final String role = Role.JOGADOR;


    // @OneToMany(mappedBy = "jogador")
    // private List<Pagamento> pagamentos;

    public Jogador(){}

    public Jogador(String nome, String email, LocalDate datanasc,String password) {
        this.nome = nome;
        this.email = email;
        this.datanasc = datanasc;
        this.password = password;

        // this.pagamentos = null;
    }

    public Jogador(Long cod_jogador) {
        this.cod_jogador = cod_jogador;
    }

    public Jogador(String nome) {
        this.nome = nome;
    }
    

    public Long getCod_jogador() {
        return cod_jogador;
    }
    public void setCod_jogador(Long cod_jogador) {
        this.cod_jogador = cod_jogador;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDatanasc() {
        return datanasc;
    }
    public void setDatanasc(LocalDate datanasc) {
        this.datanasc = datanasc;
    }
    public String getRole() {
        return role;
    }




//     public List<Pagamento> getPagamentos() {
//         return pagamentos;
//     }
//     public void setPagamentos(List<Pagamento> pagamentos) {
//         this.pagamentos = pagamentos;
//     }
}
