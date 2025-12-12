package ifsc.joe.domain.impl;

import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Guerreiro;
import ifsc.joe.domain.interfaces.ComMontaria;

// implementação do Cavaleiro

public class Cavaleiro extends Personagem implements Guerreiro, ComMontaria {

    // Seus atributos
    public static final String NOME_IMAGEM = config.getCavaleiroImagemNome(); //"cavaleiro";
    public static final String NOME_IMAGEM_DESMONTADO = config.getCavaleiroImagemDesmontado();
    private static final int FORCA_ATAQUE = config.getCavaleiroForcaAtaque(); //25;
    private static final int DEFESA = config.getCavaleiroDefesa(); //10;
    private static final int ALCANCE_ATAQUE = config.getCavaleiroAlcance();//50;
    private static final int VELOCIDADE_MONTADO = config.getCavaleiroVelocidadeMontado();//15;
    private static final int VELOCIDADE_DESMONTADO = config.getCavaleiroVelocidadeDesmontado();
    private static final String TIPO_MONTARIA = config.getCavaleiroTipoMontaria();//"Cavalo";
    private static final int VIDA_TOTAL = config.getCavaleiroVidaInicial(); //150;
    private boolean montado;

    public Cavaleiro(int x, int y) {
        super(x, y, NOME_IMAGEM, VIDA_TOTAL, VELOCIDADE_MONTADO);
        this.montado = true;
        //System.out.println(" Cavaleiro criado em ("+ x+ ","+ y +")");
    }

    // métodos definidos nas interfaces ;
    // implementação deles:
    @Override
    public int getForcaAtaque() {
        return FORCA_ATAQUE;
    }

    @Override
    public void atacar() {
        alterAtaque();
        System.out.println("Cavaleiro atacando com lança!:" + FORCA_ATAQUE);
    }

    @Override
    public boolean ataque (Personagem alvo) {
        if (alvo == null || alvo.estaMorto()) {
            return false;
        } else if (distanciaAlvo(alvo) > ALCANCE_ATAQUE) {
            System.out.println("Alvo fora de alcance!");
            return false;
        }

        alterAtaque();

        int danoFinal = FORCA_ATAQUE;

        if (alvo instanceof Guerreiro) {
            danoFinal = ((Guerreiro) alvo).defender(FORCA_ATAQUE);
        }

        alvo.receberDano(danoFinal);
        System.out.println("Cavaleiro causou " + danoFinal + " de dano.");
        return true;
    }

    @Override
    public int defender (int dano) {
        int danoReduzido = Math.max(0, dano - DEFESA);
        System.out.println("Cavaleiro defendeu! Dano: " + dano + " -> " + danoReduzido);
        return danoReduzido;
    }

    @Override
    public int getAlcanceAtaque() {
        return ALCANCE_ATAQUE;
    }

    @Override
    public boolean estaMontado() {
        return montado;
    }

    // Alterna a montaria.
    @Override
    public void alternarMontaria() {
        this.montado = !this.montado;

        this.nomeImagem = montado ? NOME_IMAGEM : NOME_IMAGEM_DESMONTADO;

        this.velocidade = montado ? VELOCIDADE_MONTADO : VELOCIDADE_DESMONTADO;

        carregarImagens();

    }

    @Override
    public int getVelocidadeMontado() {
        return VELOCIDADE_MONTADO;
    }

    @Override
    public int getVelocidadeDesmontado() {
        return VELOCIDADE_DESMONTADO;
    }

    @Override
    public String getTipoMontaria() {
        return TIPO_MONTARIA;
    }

    //@Override
    //protected int getVelocidadeBase() {
    //    return VELOCIDADE_MOVIMENTO;
   // }

}