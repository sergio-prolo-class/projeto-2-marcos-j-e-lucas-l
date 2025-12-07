package ifsc.joe.domain.impl;

// Essa classe implementa a classe personagem e uma interface;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.interfaces.Coletador;

public class Aldeao extends Personagem implements Coletador {

    // as informações só do aldeão ficaram isoladas das demais;
    public static final String NOME_IMAGEM = "aldeao";
    private static final int CAPACIDADE_COLETA = 10;

    public Aldeao(int x, int y) {
        super(x, y, NOME_IMAGEM);
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

    public void atacar() {
        alterAtaque();
    }
}