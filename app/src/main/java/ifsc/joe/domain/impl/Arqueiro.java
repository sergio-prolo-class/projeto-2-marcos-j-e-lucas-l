package ifsc.joe.domain.impl;

import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Coletador;
import ifsc.joe.domain.interfaces.Guerreiro;
import ifsc.joe.enums.TipoRecurso;

// implementação do arqueiro;
// bem enxuta 

// Configurações padões, centralizar todas;
//import ifsc.joe.config.ConfiguracaoJogo;

public class Arqueiro extends Personagem implements Guerreiro, Coletador {

    public static final String NOME_IMAGEM = config.getArqueiroImagemNome();
    private static final int FORCA_ATAQUE = config.getArqueiroForcaAtaque();
    private static final int DEFESA = config.getArqueiroDefesa();
    private static final int ALCANCE_ATAQUE = config.getArqueiroAlcance();
    private static final int VIDATOTAL = config.getArqueiroVidaInicial();
    private static final int VELOCIDADE_MOVIMENTO = config.getArqueiroVelocidade();
    private static final int FLECHAS_POR_MADEIRA = config.getArqueiroFlechasPorMadeira();
    private int flechas = config.getArqueiroFlechas();

    public Arqueiro(int x, int y) {
        super(x, y, NOME_IMAGEM, VIDATOTAL, VELOCIDADE_MOVIMENTO);
       // System.out.println(" Arqueiro criado em ("+ x+ ","+ y +")");
    }

    public int getFlechas() {
        return flechas;
    }

    // Método para somar flechas quando coleta madeira.
    public void coletarMadeira() {
        flechas += FLECHAS_POR_MADEIRA;
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

        if (flechas <= 0) {
            config.getArqueiroAvisoSemFlechas();
            return false;
        }

        alterAtaque();

        int danoFinal = FORCA_ATAQUE;

        if (alvo instanceof Guerreiro) {
            danoFinal = ((Guerreiro) alvo).defender(FORCA_ATAQUE);
        }

        alvo.receberDano(danoFinal);

        flechas--;
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

    // Métodos da interface coletador.

    @Override
    public boolean podeColetarTipo(TipoRecurso tipo) {
        // Aldeão coleta trigo e ouro.
        return tipo == TipoRecurso.TRIGO ||
                tipo == TipoRecurso.MADEIRA;
    }

    @Override
    public int getRaioColeta() {
        return 50;  // Raio de coleta do aldeão
    }

}