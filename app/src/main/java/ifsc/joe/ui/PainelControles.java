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
    private JButton coletarButton;
    private JButton montarDesmontarButton;
    private JButton buttonCima;
    private JButton buttonEsquerda;
    private JButton buttonBaixo;
    private JButton buttonDireita;

    public PainelControles() {
        this.sorteio = new Random();
        configurarListeners();
        configurarRadiobuttons();
    }

    /**
     * Configura todos os listeners dos botões.
     */
    private void configurarListeners() {
        configurarBotoesMovimento();
        configurarBotoesCriacao();
        configurarBotaoAtaque();
        configurarBotaoColeta();
        configurarBotaoMontaria();
    }

    // configurações dos botões de tipos;
    private void configurarRadiobuttons() {

        ButtonGroup grupoFiltro = new ButtonGroup();
        grupoFiltro.add(todosRadioButton);
        grupoFiltro.add(aldeaoRadioButton);
        grupoFiltro.add(arqueiroRadioButton);
        grupoFiltro.add(cavaleiroRadioButton);

        todosRadioButton.setSelected(true);

        // Pega a botão selecionado;
        todosRadioButton.addActionListener(e -> 
            getTela().setFiltrosPersonagem(Move.TODOS)
        );

        aldeaoRadioButton.addActionListener(e -> 
            getTela().setFiltrosPersonagem(Move.ALDEAO)
        );

        arqueiroRadioButton.addActionListener(e -> 
            getTela().setFiltrosPersonagem(Move.ARQUEIRO)
        );

        cavaleiroRadioButton.addActionListener(e -> 
            getTela().setFiltrosPersonagem(Move.CAVALEIRO)
        );

    }

    /**
     * Configura todos os listeners dos botões de movimento
     */
    private void configurarBotoesMovimento() {
        buttonCima.addActionListener(e -> getTela().movimentarPersonagens(Direcao.CIMA));
        buttonBaixo.addActionListener(e -> getTela().movimentarPersonagens(Direcao.BAIXO));
        buttonEsquerda.addActionListener(e -> getTela().movimentarPersonagens(Direcao.ESQUERDA));
        buttonDireita.addActionListener(e -> getTela().movimentarPersonagens(Direcao.DIREITA));

        // logs das movimentações:
        buttonBaixo.addActionListener(e ->  System.out.println(getTela().logsDosFiltros()));
        buttonCima.addActionListener(e ->  System.out.println(getTela().logsDosFiltros()));
        buttonDireita.addActionListener(e ->  System.out.println(getTela().logsDosFiltros()));
        buttonEsquerda.addActionListener(e ->  System.out.println(getTela().logsDosFiltros()));

    }

    /**
     * Configura todos os listeners dos botões de criação
     */
    private void configurarBotoesCriacao() {
        bCriaAldeao.addActionListener(e -> criarAldeaoAleatorio());
        bCriaArqueiro.addActionListener(e -> criarArqueiroAleatorio());
        bCriaCavaleiro.addActionListener(e -> criarCavaleiroAleatorio());

        // Logs de criação:
        bCriaAldeao.addActionListener(e -> System.out.println(getTela().logsDeCriacao("ALDEAO")));
        bCriaArqueiro.addActionListener(e -> System.out.println(getTela().logsDeCriacao("ARQUEIRO")));
        bCriaCavaleiro.addActionListener(e -> System.out.println(getTela().logsDeCriacao("CAVALEIRO")));

    }

    /**
     * Configura o listener do botão de ataque
     */
    private void configurarBotaoAtaque() {
        atacarButton.addActionListener(e -> atacarPersonagens());
    }

    // Botão de coleta.
    private void configurarBotaoColeta() {
        coletarButton.addActionListener(e -> getTela().coletarRecursos());
    }

    // Botão de montaria.
    private void configurarBotaoMontaria() {
        montarDesmontarButton. addActionListener(e -> getTela().alternarMontaria());
    }

    private void atacarPersonagens() {
        if (todosRadioButton.isSelected()) {
            getTela().combate();
        } else if (aldeaoRadioButton.isSelected()) {
            getTela().combatePorTipo(Aldeao.class);
        } else if (arqueiroRadioButton.isSelected()) {
            getTela().combatePorTipo(Arqueiro.class);
        } else if (cavaleiroRadioButton.isSelected()) {
            getTela().combatePorTipo(Cavaleiro.class);
        }
    }

    /**
     * Cria um personagens em posição aleatória na tela.
     */
    private void criarAldeaoAleatorio() {
        final int PADDING = 50;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarAldeao(posX, posY);
    }

    private void criarCavaleiroAleatorio() {
        final int PADDING = 50;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarCavaleiro(posX, posY);
    }

    private void criarArqueiroAleatorio() {
        final int PADDING = 50;
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);

        getTela().criarArqueiro(posX, posY);
    }

    /**
     * Exibe mensagem informando que a funcionalidade ainda não foi implementada.
     */
    private void mostrarMensagemNaoImplementado(String funcionalidade) {
        JOptionPane.showMessageDialog(
                painelPrincipal,
                "Preciso ser implementado",
                funcionalidade,
                JOptionPane.INFORMATION_MESSAGE
        );
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