package ifsc.joe.domain.interfaces;

// Para mais detalhes sobre a inteface, leia o docs que está nesse mesmo diretorio;

import ifsc.joe.domain.Personagem;

public interface Guerreiro {
    // métodos basicos;
    void atacar();
    boolean ataque(Personagem Alvo);
    int getForcaAtaque();
    int defender(int dano);
    int getAlcanceAtaque();
}


