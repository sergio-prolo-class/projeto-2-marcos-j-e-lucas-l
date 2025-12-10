package ifsc.joe.domain.impl;

import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Guerreiro;

// implementação do arqueiro;
// bem enxuta 

public class Arqueiro extends Personagem implements Guerreiro {

    public static final String NOME_IMAGEM = "arqueiro";
    private static final int FORCA_ATAQUE = 15;
    private static final int DEFESA = 5;
    private static final int ALCANCE_ATAQUE = 150;

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
    public boolean ataque(Personagem alvo) {
        if (alvo == null || alvo.estaMorto()) {
            return false;
        }

        if (distanciaAlvo(alvo) > ALCANCE_ATAQUE) {
            System.out.println("Alvo fora de alcance");
            return false;
        }

        alterAtaque();

        int danoFinal = FORCA_ATAQUE;

        if (alvo instanceof Guerreiro) {
            danoFinal = ((Guerreiro) alvo).defender(FORCA_ATAQUE);
        }

        alvo.receberDano(danoFinal);
        System.out.println("Arqueiro causou " + danoFinal + " de dano!");

        return true;
    }

    @Override
    public int getForcaAtaque(){
        return FORCA_ATAQUE;
    }

    @Override
    public int defender(int dano) {
        int danoReduzido = Math.max(0, dano - DEFESA);
        System.out.println("Arqueiro defendeu! Dano: " + dano + " -> " + danoReduzido);
        return danoReduzido;
    }

    @Override
    public int getAlcanceAtaque() {
        return ALCANCE_ATAQUE;
    }

}