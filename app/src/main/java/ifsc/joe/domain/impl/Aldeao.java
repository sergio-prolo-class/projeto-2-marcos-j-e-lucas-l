package ifsc.joe.domain.impl;

// Essa classe implementa a classe personagem e uma interface;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Coletador;
import ifsc.joe.domain.interfaces.ComMontaria;
import ifsc.joe.enums.TipoRecurso;

public class Aldeao extends Personagem implements Coletador, ComMontaria {

    // as informações só do aldeão ficaram isoladas das demais;
    public static final String NOME_IMAGEM = "aldeao";
    public static final String NOME_IMAGEM_MONTADO = "aldeaoMontado";
    private static final int CAPACIDADE_COLETA = 10;
    private static final int VELOCIDADE_BASE = 10;
    private static final int VELOCIDADE_MONTADO = 18;
    private static final String TIPO_MONTARIA = "Jegue";
    private boolean montado;

    public Aldeao(int x, int y) {
        super(x, y, NOME_IMAGEM);
        this.montado = false;
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

    // Métodos da interface ComMontaria.
    @Override
    public boolean estaMontado() {
        return montado;
    }

    @Override
    public void alternarMontaria() {
        this.montado = !this.montado;

        this.nomeImagemBase = montado ? NOME_IMAGEM_MONTADO : NOME_IMAGEM;
    }

    @Override
    public int getVelocidadeMovimento() {
        return montado ? VELOCIDADE_MONTADO : VELOCIDADE_BASE;
    }

    @Override
    public int getVelocidadeMontado() {
        return VELOCIDADE_MONTADO;
    }

    @Override
    public int getVelocidadeDesmontado() {
        return VELOCIDADE_BASE;
    }

    @Override
    public String getTipoMontaria() {
        return TIPO_MONTARIA;
    }

    @Override
    protected int getVelocidadeBase() {
        return getVelocidadeMovimento();
    }

}