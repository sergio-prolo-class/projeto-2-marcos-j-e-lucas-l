package ifsc.joe.domain.impl;

import ifsc.joe.domain.Recursos;
import ifsc.joe.enums.TipoRecurso;

public class Ouro extends Recursos {

    public static final String NOME_IMAGEM = "ouro";

    public Ouro (int x, int y) {
        super(x, y, NOME_IMAGEM, TipoRecurso.OURO);
    }

}
