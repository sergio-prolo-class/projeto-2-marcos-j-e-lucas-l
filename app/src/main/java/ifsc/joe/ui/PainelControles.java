package ifsc.joe.ui;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.util.Random;

import ifsc.joe.config.ConfiguracaoJogo;

import ifsc.joe.factory.PersonagemFactory;
import ifsc.joe.enums.TipoPersonagem;

/**
 * Classe responsável por gerenciar os controles e interações da interface.
 * Conecta os componentes visuais com a lógica do jogo (Tela).
 */
public class PainelControles {


    private final Random sorteio;
    private Tela tela;

    // valores da configuração;
    private final ConfiguracaoJogo config = new ConfiguracaoJogo();
    private final int PADDING =  config.getPersonagemPadding();

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
    private JLabel logo;

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

        bCriaAldeao.addActionListener(e -> criarPersonagemAleatorio(TipoPersonagem.ALDEAO));
        bCriaArqueiro.addActionListener(e -> criarPersonagemAleatorio(TipoPersonagem.ARQUEIRO));
        bCriaCavaleiro.addActionListener(e -> criarPersonagemAleatorio(TipoPersonagem.CAVALEIRO));

        // Logs de criação:
        bCriaAldeao.addActionListener(e -> System.out.println(getTela().logsDeCriacao("ALDEAO")));
        bCriaArqueiro.addActionListener(e -> System.out.println(getTela().logsDeCriacao("ARQUEIRO")));
        bCriaCavaleiro.addActionListener(e -> System.out.println(getTela().logsDeCriacao("CAVALEIRO")));
    }

    private void criarPersonagemAleatorio(TipoPersonagem tipo) {

        if(!PersonagemFactory.podeSerCriado(tipo)) {
            mostrarMensagemErro("Tipo não pode ser criado: "+ tipo.getNome());
            System.out.println("Erro ao criar personagem");
            return;
        }
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);
        getTela().criarPersonagem(tipo, posX, posY);
    }

    private void criarPersonagemCompletamenteAleatorio() {
        int posX = sorteio.nextInt(painelTela.getWidth() - PADDING);
        int posY = sorteio.nextInt(painelTela.getHeight() - PADDING);
        getTela().criarPersonagemAleatorio(posX, posY);
    }

    /**
     * Configura o listener do botão de ataque
     */
    private void configurarBotaoAtaque() {
        atacarButton.addActionListener(e -> atacarPersonagens());
    }

    // Método para criar o botão coleta.
    private void configurarBotaoColeta() {
        coletarButton.addActionListener(e -> getTela().coletarRecursos());
    }

    // Método para criar o botão de montar e desmontar.
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

    private void mostrarMensagemErro(String msg) {
        JOptionPane.showMessageDialog(
            painelPrincipal,
            msg,
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
}