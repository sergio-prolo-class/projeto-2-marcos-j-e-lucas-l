package ifsc.joe.domain.impl;

import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Guerreiro;
import ifsc.joe.domain.interfaces.ComMontaria;

// implementação do Cavaleiro

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {

    // Seus atributos
    public static final String NOME_IMAGEM = "cavaleiro";
    private static final int FORCA_ATAQUE = 25;
    private static final int VELOCIDADE_MOVIMENTO = 15;
    private static final String TIPO_MONTARIA = "Cavalo";

    public Cavaleiro(int x, int y) {
        super(x, y, NOME_IMAGEM);
    }

    // métodos definidos nas interfaces ;
    // implementação deles:
    @Override
    public int getForcaAtaque() {
        return FORCA_ATAQUE;
    }

    @Override
    public int getVelocidadeDeMovimento() {
        return VELOCIDADE_MOVIMENTO;
    }

    @Override
    public void atacar() {
        alterAtaque();
        System.out.println("Cavaleiro atacando com lança!:" + FORCA_ATAQUE);
    }

    @Override
    public String getTipoDeMontaria() {
        return TIPO_MONTARIA;
    }

    @Override
    protected int getVelocidadeBase() {
        return VELOCIDADE_MOVIMENTO;
    }


    @Override 
    public void defender(){
        System.out.println("ataque defendido");
    }
}