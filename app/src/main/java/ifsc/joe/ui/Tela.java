package ifsc.joe.ui;

// classe abstrata de personagem;
import ifsc.joe.domain.Personagem;

import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import java.util.stream.Collectors;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tela extends JPanel {

    //private final Set<Aldeao> aldeoes;
    //private final Set<Cavaleiro> cavaleiros;

    //conforme as atividades do professor, não precisa de varias estruturas de dados para retratar os personagem diferentes;
    private final Set<Personagem> personagens;
    // Atraves do polimorfismo será feito os filtros e movimentações separados;

    // variável para pegar o tipo selecionado;
    private String filtroAtual;

    public Tela() {
        this.setBackground(Color.white);
        this.personagens = new HashSet<>();

        // Por padrão todos os tipo são selecionados;
        this.filtroAtual = Move.TODOS;

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

    // ajusta o tipo na variável do tipo;
    public void setFiltrosPersonagem(String tipo) {
        this.filtroAtual = tipo;
    }


    private boolean personagemCorrespondeAoFiltro(Personagem personagens) {
        switch(filtroAtual) {
            case "TODOS":
                return true;
            case "ALDEAO":
                return personagens instanceof Aldeao;
            case "ARQUEIRO":
                return personagens instanceof Arqueiro;
            case "CAVALEIRO":
                return personagens instanceof Cavaleiro;
            default:
                return false;
        }
    }

    private Set<Personagem> getPersonagensFiltrados() {
        return personagens.stream()
                .filter(this::personagemCorrespondeAoFiltro)
                .collect(Collectors.toSet());
    }

    /**
     * Atualiza as coordenadas X ou Y de todos os aldeoes
     *
     * @param direcao direcao para movimentar
     */

    // nova implementação de movimentação:
    public void movimentarPersonagens(Direcao direcao) {
        Set<Personagem> personagensFiltrados = getPersonagensFiltrados();

        personagensFiltrados.forEach(personagem -> 
            personagem.mover(direcao, this.getWidth(), this.getHeight())
        );
        this.repaint();

    }
    // antiga implmentação:

    // public void movimentarAldeoes(Direcao direcao) {
    //     //TODO preciso ser melhorado

    //     this.personagens.forEach(personagens -> personagens.mover(direcao, this.getWidth(), this.getHeight()));

    //     // Depois que as coordenadas foram atualizadas é necessário repintar o JPanel
    //     this.repaint();
    // }


    /**
     * Altera o estado do aldeão de atacando para não atacando e vice-versa
     */
    public void atacarAldeoes() {
        Set<Personagem> personagensFiltrados = getPersonagensFiltrados();

        personagensFiltrados.forEach(Personagem::alterAtaque);

        //TODO preciso ser melhorado

        // Percorrendo a lista de aldeões e pedindo para todos atacarem
        //this.aldeoes.forEach(Aldeao::atacar);

        // Fazendo o JPanel ser redesenhado
        this.repaint();
    }

    // Logs para testar o funcionamento;
    public String logsDosFiltros() {
        Set<Personagem> filtrados = getPersonagensFiltrados();
        return String.format("Filtrado: %s | Personagens afetados: %d de %d", filtroAtual, filtrados.size(), personagens.size());
    }

    public String logsDeCriacao(String p) {
        String iso = LocalDateTime.now().toString(); // 2000-12-08
        return String.format("Personagem: %s | Criado em: %s | Quatidade P.: %d", p, iso, personagens.size());
    }

}