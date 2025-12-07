package ifsc.joe.ui;

// classe abstrata de personagem;
import ifsc.joe.domain.Personagem;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Tela extends JPanel {

    //private final Set<Aldeao> aldeoes;
    //private final Set<Cavaleiro> cavaleiros;

    //conforme as atividades do professor, não precisa de varias estruturas de dados para retratar os personagem diferentes;
    private final Set<Personagem> personagens;
    // Atraves do polimorfismo será feito os filtros e movimentações separados;

    public Tela() {
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();

        //TODO preciso ser melhorado
        //this.aldeoes = new HashSet<>();
        //this.cavaleiros = new HashSet<>();
    }

    /**
     * Method que invocado sempre que o JPanel precisa ser resenhado.
     * @param g Graphics componente de java.awt
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        this.personagens.forEach(personagens -> personagens.desenhar(g, this));
        g.dispose();

        // === antiga implementação ===
        // percorrendo a lista de aldeões e pedindo para cada um se desenhar na tela
        //this.aldeoes.forEach(aldeao -> aldeao.desenhar(g, this));
        // liberando o contexto gráfico
        //g.dispose();

    }

    /**
     * Cria um aldeao nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de aldeoes
     *
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void criarAldeao(int x, int y) {
        Aldeao aldeao = new Aldeao(x, y);
        aldeao.desenhar(super.getGraphics(), this);
        this.personagens.add(aldeao);
    }

    // Será implementada no futuro
    // public void criarCavaleiro(int x, int y) {
    //     Cavaleiro c = new Cavaleiro(x, y);
    //     c.desenhar(super.getGraphics(), this);
    //     this.personagens.add(c);
    // }

    /**
     * Atualiza as coordenadas X ou Y de todos os aldeoes
     *
     * @param direcao direcao para movimentar
     */
    public void movimentarAldeoes(Direcao direcao) {
        //TODO preciso ser melhorado

        this.personagens.forEach(personagens -> personagens.mover(direcao, this.getWidth(), this.getHeight()));

        // Depois que as coordenadas foram atualizadas é necessário repintar o JPanel
        this.repaint();
    }

    /**
     * Altera o estado do aldeão de atacando para não atacando e vice-versa
     */
    public void atacarAldeoes() {

        //TODO preciso ser melhorado

        // Percorrendo a lista de aldeões e pedindo para todos atacarem
        //this.aldeoes.forEach(Aldeao::atacar);

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }
}