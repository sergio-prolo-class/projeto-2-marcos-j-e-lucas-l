package ifsc.joe.ui;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.util.Random;

/**
 * Classe responsável por gerenciar os controles e interações da interface.
 * Conecta os componentes visuais com a lógica do jogo (Tela).
 */
public class PainelControles {

    private final Random sorteio;
    private Tela tela;

    // Componentes da interface (gerados pelo Form Designer)
    private JPanel painelPrincipal;
    private JPanel painelTela;
    private JPanel painelLateral;
    private JButton bCriaAldeao;
    private JButton bCriaArqueiro;
    private JButton bCriaCavaleiro;
    private JRadioButton todosRadioButton;
    private JRadioButton aldeaoRadioButton;
    private JRadioButton arqueiroRadioButton;
    private JRadioButton cavaleiroRadioButton;
    private JButton atacarButton;
    private JButton buttonCima;
    private JButton buttonEsquerda;
    private JButton buttonBaixo;
    private JButton buttonDireita;
    private JLabel logo;

    public PainelControles() {
        this.sorteio = new Random();
        configurarListeners();
    }

    /**
     * Configura todos os listeners dos botões.
     */
    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoAtaque();
    }

    /**
     * Configura todos os listeners dos botões de movimento
     */
    private void configurarBotoesMovimento() {
        buttonCima.addActionListener(e -> movimentoPersonagens(Direcao.CIMA));
        buttonBaixo.addActionListener(e -> movimentoPersonagens(Direcao.BAIXO));
        buttonEsquerda.addActionListener(e -> movimentoPersonagens(Direcao.ESQUERDA));
        buttonDireita.addActionListener(e -> movimentoPersonagens(Direcao.DIREITA));
    }

    // Método para movimento dos personagens de acordo com o botão selecionado.
    private void movimentoPersonagens(Direcao direcao) {
        if (todosRadioButton.isSelected()) {
            getTela().movimentarPersonagens(direcao);
        } else if (aldeaoRadioButton.isSelected()) {
            getTela().movimentarTipos(direcao, Aldeao.class);
        } else if (arqueiroRadioButton.isSelected()) {
            getTela().movimentarTipos(direcao, Arqueiro.class);
        } else if (cavaleiroRadioButton.isSelected()) {
            getTela().movimentarTipos(direcao, Cavaleiro.class);
        }
    }

    /**
     * Cria um aldeão em posição aleatória na tela.
     */
    private void criarAldeaoAleatorio() {
        final int PADDING = 50;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarAldeao(posX, posY);
    }

    /*Cria um Arqueiro em posição aleatória na tela.*/
    private void criarArqueiroAleatorio() {
        final int PADDING = 50;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarArqueiro(posX, posY);
    }

    /*Cria um Cavaleiro em posição aleatória na tela.*/
    private void criarCavaleiroAleatorio() {
        final int PADDING = 50;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarCavaleiro(posX, posY);
    }

    /**
     * Configura todos os listeners dos botões de criação
     */
    private void configurarBotoesCriacao() {
        bCriaAldeao.addActionListener(e -> criarAldeaoAleatorio());
        bCriaArqueiro.addActionListener(e -> criarArqueiroAleatorio());
        bCriaCavaleiro.addActionListener(e -> criarCavaleiroAleatorio());
    }

    // Método para o ataque dos personagens selecionados no botão.
    private void atacarPersonagens() {
        if (todosRadioButton.isSelected()){
            getTela().atacarGeral();
        } else if (aldeaoRadioButton.isSelected()) {
            getTela().atacarTipos(Aldeao.class);
        } else if (arqueiroRadioButton.isSelected()) {
            getTela().atacarTipos(Arqueiro.class);
        } else if (cavaleiroRadioButton.isSelected()) {
            getTela().atacarTipos(Cavaleiro.class);
        }
    }

    // Configura o botão de ataque para o botão selecionado.
    private void configurarBotaoAtaque() {
        atacarButton.addActionListener(e -> atacarPersonagens());
    }

    /**
     * Obtém a referência da Tela com cast seguro.
     */
    private Tela getTela() {
        if (tela == null) {
            tela = (Tela) painelTela;
        }
        return tela;
    }

    /**
     * Retorna o painel principal para ser adicionado ao JFrame.
     */
    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    /**
     * Método chamado pelo Form Designer para criar componentes customizados.
     * Este método é invocado antes do construtor.
     */
    private void createUIComponents() {
        this.painelTela = new Tela();
    }
}