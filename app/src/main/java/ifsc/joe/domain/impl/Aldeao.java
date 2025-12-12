package ifsc.joe.domain.impl;

// Essa classe implementa a classe personagem e uma interface;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Coletador;

// Configurações padões, centralizar todas;
//import ifsc.joe.config.ConfiguracaoJogo;

public class Aldeao extends Personagem implements Coletador {

    // Classe responsavel por centralizar todas as cnfigurações;
    //public static final ConfiguracaoJogo config = new ConfiguracaoJogo();

    // as informações só do aldeão ficaram isoladas das demais;
    public static final String NOME_IMAGEM = config.getAldeaoImagemNome();
    private static final int CAPACIDADE_COLETA = config.getAldeaoCapacidadeColeta();
    private static final int valorDeVida = config.getAldeaoVidaInicial();
    private static final int velocidadeDeMovimento = config.getAldeaoVelocidade();

    public Aldeao(int x, int y) {
        super(x, y, NOME_IMAGEM, valorDeVida, velocidadeDeMovimento);
        //System.out.println(" Aldeão criado em ("+ x+ ","+ y +")");
    }

    // Sem muito segredo até aqui;
    // subscrição de alguns métodos;
    @Override
    public int getCapacidadeColeta() {
        return CAPACIDADE_COLETA;
    }

    @Override
    public void coletar() {
        System.out.println("Aldeão coletando recursos...");
    }

}