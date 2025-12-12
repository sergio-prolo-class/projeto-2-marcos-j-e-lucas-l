package ifsc.joe.domain.interfaces;

// Para mais detalhes sobre a inteface, leia o docs que está nesse mesmo diretorio;

public interface ComMontaria {
    // métodos basicos;
    int getVelocidadeMovimento();
    String getTipoMontaria();

    boolean estaMontado();
    void alternarMontaria();
    int getVelocidadeMontado();
    int getVelocidadeDesmontado();

}