package dw.trabalho.futebol.model;

import jakarta.persistence.Embeddable;

@Embeddable
public abstract class Role {
    public final static String ADM = "ADM";
    public final static String JOGADOR = "JOGADOR";

    private Role() {
    }
}
