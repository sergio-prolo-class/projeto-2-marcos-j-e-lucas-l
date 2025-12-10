package ifsc.joe.ui;

// classe abstrata de personagem;
import ifsc.joe.domain.Personagem;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.interfaces.Guerreiro;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Tela extends JPanel {

    //conforme as atividades do professor, não precisa de varias estruturas de dados para retratar os personagem diferentes;
    private final Set<Personagem> personagens;
    // Atraves do polimorfismo será feito os filtros e movimentações separados;

    public Tela() {
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();
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

    }

    /**
     * Cria um personagens nas coordenadas X e Y, desenha-o neste JPanel
     * e adiciona o mesmo na lista de personagens:
     *
     * @param x coordenada X
     * @param y coordenada Y
     */
    public void criarAldeao(int x, int y) {
        Aldeao aldeao = new Aldeao(x, y);
        aldeao.desenhar(super.getGraphics(), this);
        this.personagens.add(aldeao);
    }

    public void criarCavaleiro(int x, int y) {
        Cavaleiro cavaleiro = new Cavaleiro(x, y);
        cavaleiro.desenhar(super.getGraphics(), this);
        this.personagens.add(cavaleiro);
    }

    public void criarArqueiro(int x, int y) {
        Arqueiro arqueiro = new Arqueiro(x, y);
        arqueiro.desenhar(super.getGraphics(), this);
        this.personagens.add(arqueiro);
    }

    // Método para movimentar todos os Personagens.
    public void movimentarPersonagens(Direcao direcao) {
        //TODO preciso ser melhorado

        this.personagens.forEach(personagens -> personagens.mover(direcao, this.getWidth(), this.getHeight()));

        // Depois que as coordenadas foram atualizadas é necessário repintar o JPanel
        this.repaint();
    }

    // Método para movimento os personagens por tipos.
    public void movimentarTipos(Direcao direcao, Class<? extends Personagem> tipo) {
        this.personagens
                .stream().filter(p -> tipo.isInstance(p))
                .forEach(p -> p.mover(direcao, this.getWidth(), this.getHeight()));
        this.repaint();
    }

    // Guerreiros atacam unidades próximas causando dano a elas.
    public void combate() {
        personagens.removeIf(Personagem::estaMorto);

        personagens.stream()
                .filter(p -> p instanceof Guerreiro)
                .forEach(atacante -> {
                    Guerreiro guerreiro = (Guerreiro) atacante;

                    Personagem alvoMaisProximo = personagens. stream()
                            .filter(p -> p != atacante)
                            .filter(p -> ! p.estaMorto())
                            . min((p1, p2) -> Double.compare(
                                    atacante.distanciaAlvo(p1),
                                    atacante.distanciaAlvo(p2)
                            ))
                            .orElse(null);
                    if (alvoMaisProximo != null) {
                        guerreiro.ataque(alvoMaisProximo);
                    }
                });
        this.repaint();
    }

    // Combate por diferenciação de tipo.
    public void combatePorTipo (Class<? extends Personagem> tipo) {
        personagens.removeIf(Personagem::estaMorto);

        personagens.stream()
                .filter(tipo::isInstance)
                .filter(p -> p instanceof Guerreiro)
                .forEach(atacante -> {
                    Guerreiro guerreiro = (Guerreiro) atacante;

                    Personagem alvoMaisProximo = personagens.stream()
                            .filter(p -> p != atacante)
                            .filter(p -> !p.estaMorto())
                            .min((p1, p2) -> Double.compare(
                                    atacante.distanciaAlvo(p1),
                                    atacante.distanciaAlvo(p2)
                            ))
                            .orElse(null);

                    if (alvoMaisProximo != null) {
                        guerreiro.ataque(alvoMaisProximo);
                    }
                });
        this.repaint();
    }

}