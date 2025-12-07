package ifsc.joe.domain.impl;

import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Guerreiro;

// implementação do arqueiro;
// bem enxuta 

public class Arqueiro extends Personagem implements Guerreiro {

    public static final String NOME_IMAGEM = "arqueiro";
    private static final int FORCA_ATAQUE = 15;

    public Arqueiro(int x, int y) {
        super(x, y, NOME_IMAGEM);
    }

    // Implementação dos métodos das interfaces:
    @Override
    public void atacar(){
        alterAtaque();
        System.out.println("Arqueiro atirando flechas! Força:"+ FORCA_ATAQUE);
    }

    @Override
    public int getForcaAtaque(){
        return FORCA_ATAQUE;
    }

    @Override
    public void defender(){
        System.out.println("Arqueiro defendeu...");
    }
}