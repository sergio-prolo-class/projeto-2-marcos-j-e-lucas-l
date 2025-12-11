package ifsc.joe.domain.impl;

import ifsc.joe.domain.Recursos;
import ifsc.joe.enums.TipoRecurso;

public class Trigo extends Recursos {

    public static final String NOME_IMAGEM = "trigo";

    public Trigo (int x, int y) {
        super(x, y, NOME_IMAGEM, TipoRecurso.TRIGO);
    }

}