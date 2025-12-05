package ifsc.joe.ui;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.domain.impl.Personagem;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Tela extends JPanel {

    private final Set<Personagem> personagem;

    public Tela() {

        //TODO preciso ser melhorado

        this.setBackground(Color.white);
        this.personagem = new HashSet<>();
    }

    /**
     * Method que invocado sempre que o JPanel precisa ser resenhado.
     * @param g Graphics componente de java.awt
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //TODO preciso ser melhorado

        // percorrendo a lista de aldeões e pedindo para cada um se desenhar na tela
        this.personagem.forEach(personagem -> personagem.desenhar(g, this));

        // liberando o contexto gráfico
        g.dispose();
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
        this.personagem.add(aldeao);
    }

    // Mesmo método para criar o arqueiro.
    public void criarArqueiro(int x, int y) {
        Arqueiro arqueiro = new Arqueiro(x, y);
        arqueiro.desenhar(super.getGraphics(), this);
        this.personagem.add(arqueiro);
    }

    // Mesmo método para criar o cavaleiro.
    public void criarCavaleiro(int x, int y) {
        Cavaleiro cavaleiro = new Cavaleiro(x, y);
        cavaleiro.desenhar(super.getGraphics(), this);
        this.personagem.add(cavaleiro);
    }

    // Movimentação de todos os personagens
    public void movimentarPersonagens(Direcao direcao) {
        //TODO preciso ser melhorado

        this.personagem.forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));

        // Depois que as coordenadas foram atualizadas é necessário repintar o JPanel
        this.repaint();
    }

    // Movimentação de personagens por cada tipo
    public void movimentarTipos(Direcao direcao, Class<? extends Personagem> tipo) {
        this.personagem
                .stream().filter(p -> tipo.isInstance(p))
                .forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));
        this.repaint();
    }


    // Método de ataque geral.
    public void atacarGeral() {

        // Percorre a lista de personagens, método de atacar geral.
        this.personagem.forEach(Personagem::atacar);

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }

    // Método de ataque por tipo de personagem.
    public void atacarTipos(Class<? extends Personagem> tipo) {
        this.personagem.stream()
                .filter(p -> tipo.isInstance(p))
                .forEach(Personagem::atacar);
        this.repaint();
    }

}