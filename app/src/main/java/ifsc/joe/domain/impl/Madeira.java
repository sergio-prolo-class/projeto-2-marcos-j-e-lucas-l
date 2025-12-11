package ifsc.joe.domain.impl;

import ifsc.joe.domain.Recursos;
import ifsc.joe.enums.TipoRecurso;

public class Madeira extends Recursos {

    public static final String NOME_IMAGEM = "tabua";

    public Madeira(int x, int y) {
        super(x, y, NOME_IMAGEM, TipoRecurso.MADEIRA);
    }

}