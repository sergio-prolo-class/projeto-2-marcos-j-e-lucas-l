package ifsc.joe.domain.impl;

// Essa classe implementa a classe personagem e uma interface;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Coletador;
import ifsc.joe.domain.interfaces.ComMontaria;
import ifsc.joe.enums.TipoRecurso;

// Configurações padões, centralizar todas;
//import ifsc.joe.config.ConfiguracaoJogo;

public class Aldeao extends Personagem implements Coletador, ComMontaria {

    // Classe responsavel por centralizar todas as cnfigurações;
    //public static final ConfiguracaoJogo config = new ConfiguracaoJogo();

    // as informações só do aldeão ficaram isoladas das demais;
    public static final String NOME_IMAGEM = config.getAldeaoImagemNome();
    public static final String NOME_IMAGEM_MONTADO = config.getAldeaoImagemMontado();
    private static final int VALOR_DE_VIDA = config.getAldeaoVidaInicial();
    private static final int VELOCIDADE_DESMONTADO = config.getAldeaoVelocidadeDesmontado();
    private static final int VELOCIDADE_MONTADO = config.getAldeaoVelocidadeMontado();
    private static final String TIPO_MONTARIA = config.getAldeaoTipoMontaria();
    private boolean montado;

    public Aldeao(int x, int y) {
        super(x, y, NOME_IMAGEM, VALOR_DE_VIDA, VELOCIDADE_DESMONTADO);
        this.montado = false;
    }

    // Sem muito segredo até aqui;
    // subscrição de alguns métodos;

    @Override
    public boolean podeColetarTipo(TipoRecurso tipo) {
        // Aldeão coleta trigo e ouro.
        return tipo == TipoRecurso.TRIGO ||
                tipo == TipoRecurso.OURO;
    }

    @Override
    public int getRaioColeta() {
        return 50;  // Raio de coleta do aldeão
    }

    @Override
    public boolean estaMontado() {
        return montado;
    }

    @Override
    public void alternarMontaria() {
        this.montado = !this.montado;

        // Atualiza imagem base
        this.nomeImagem = montado ? NOME_IMAGEM_MONTADO : NOME_IMAGEM;

        // Atualiza velocidade
        this.velocidade = montado ? VELOCIDADE_MONTADO : VELOCIDADE_DESMONTADO;

        // Recarrega as imagens
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

}