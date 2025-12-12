package ifsc.joe.domain.interfaces;

// Para mais detalhes sobre a inteface, leia o docs que está nesse mesmo diretorio;

import ifsc.joe.enums.TipoRecurso;

public interface Coletador {
    //métodos basicos;
    boolean podeColetarTipo(TipoRecurso tipo);  // Verifica se pode coletar
    int getRaioColeta();

}