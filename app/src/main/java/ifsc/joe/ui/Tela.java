package ifsc.joe.ui;

// classe abstrata de personagem;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.Recursos;

import ifsc.joe.domain.impl.*;
import ifsc.joe.domain.interfaces.Guerreiro;
import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

import java.util.stream.Collectors;

import java.time.LocalDateTime;

public class Tela extends JPanel {

    //conforme as atividades do professor, não precisa de varias estruturas de dados para retratar os personagem diferentes;
    private final Set<Personagem> personagens;
    private final Set<Recursos> recursos;
    // Atraves do polimorfismo será feito os filtros e movimentações separados;

    // variável para pegar o tipo selecionado;
    private String filtroAtual;
    private Image imagemFundo;
    private boolean recursosGerados = false;

    // Constantes para o tamanho dos recursos:
    private static final int LARGURA_RECURSO = 40;
    private static final int ALTURA_RECURSO = 40;
    private static final int DISTANCIA_MINIMA = 35;
    private static final int MARGEM_BORDA = 20;

    public Tela() {
        this.personagens = new HashSet<>();
        this.recursos = new HashSet<>();

        // Por padrão todos os tipo são selecionados;
        this.filtroAtual = Move.TODOS;

        // Carrega a imagem de fundo.
        this.imagemFundo = carregarImagemGrama("grama");

        // Listener que espera o painel ter tamanho.
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (! recursosGerados && getWidth() > 0 && getHeight() > 0) {
                    gerarRecursos();
                    recursosGerados = true;
                    System.out.println("Recursos gerados com sucesso!  Largura: " + getWidth() + " Altura: " + getHeight());
                }
            }
        });

    }

    // Método para criar a grama.
    private Image carregarImagemGrama(String arquivo) {
        try {
            return new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource
                    ("./" + arquivo + ".png"))).getImage();
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem de fundo: " + arquivo);
            return null;
        }
    }

    // Método para gerar recursos em lugares aleatórios nos 10% superior e inferior do mapa.
    private void gerarRecursos() {
        Random random = new Random();
        int quantidade = Recursos.getQuantidade();
        int tentativasMaximas = 500; // Recurso para evitar loop infinito.

        // Gera 15 tábuas
        for (int i = 0; i < quantidade; i++) {
            Madeira madeira = antiColisao(random, tentativasMaximas, Madeira.class);
            if (madeira != null) {
                recursos.add(madeira);
            }
        }

        // Gera 15 Ouros
        for (int i = 0; i < quantidade; i++) {
            Ouro ouro = antiColisao(random, tentativasMaximas, Ouro.class);
            if (ouro != null) {
                recursos.add(ouro);
            }
        }

        // Gera 15 Trigos.
        for (int i = 0; i < quantidade; i++) {
            Trigo trigo = antiColisao(random, tentativasMaximas, Trigo. class);
            if (trigo != null) {
                recursos. add(trigo);
            }
        }

        System.out.println("Recursos gerados: " + recursos.size() + " (15 Madeiras, 15 Ouros, 15 Trigos)");
    }

    // Método para os recursos não ficarem um em cima do outro.
    private <T extends Recursos> T antiColisao (Random random, int tentativasMaximas, Class<T> tipoRecurso) {
        for (int tentativa = 0; tentativa < tentativasMaximas; tentativa++) {
            // Calcula a área utilizável com margem.
            int larguraUtil = getWidth() - (2 * MARGEM_BORDA) - LARGURA_RECURSO;
            int alturaZona = (int)(getHeight() * 0.15);  // Aumentado de 0.1 para 0.15 (15% em vez de 10%)

            // posX aleatória.
            int posX = MARGEM_BORDA + random.nextInt(Math.max(1, larguraUtil));

            // posY nos 15% superior ou inferior.
            int posY;
            if (random.nextBoolean()) {
                // 15% superior
                posY = MARGEM_BORDA + random.nextInt(Math.max(1, alturaZona - MARGEM_BORDA));
            } else {
                // 15% inferior
                int inicioZonaInferior = (int)(getHeight() * 0.85);  // Mudado de 0.9 para 0.85
                int espacoDisponivel = getHeight() - inicioZonaInferior - MARGEM_BORDA - ALTURA_RECURSO;
                posY = inicioZonaInferior + random.nextInt(Math.max(1, espacoDisponivel));
            }

            // Verifica colisão
            if (! verificaColisao(posX, posY)) {
                try {
                    if (tipoRecurso == Madeira.class) {
                        return tipoRecurso.cast(new Madeira(posX, posY));
                    } else if (tipoRecurso == Ouro.class) {
                        return tipoRecurso. cast(new Ouro(posX, posY));
                    } else if (tipoRecurso == Trigo.class) {
                        return tipoRecurso.cast(new Trigo(posX, posY));
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao criar recurso: " + e.getMessage());
                }
            }
        }
        System.out.println("FALHA:  Não foi possível posicionar " + tipoRecurso.getSimpleName() + " após " + tentativasMaximas + " tentativas");
        return null;
    }

    private boolean verificaColisao(int novoX, int novoY) {
        for (Recursos recurso : recursos) {
            int distanciaX = Math.abs(recurso.getPosX() - novoX);
            int distanciaY = Math.abs(recurso.getPosY() - novoY);

            double distancia = Math.sqrt(distanciaX * distanciaX + distanciaY * distanciaY);

            if (distancia < DISTANCIA_MINIMA) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method que invocado sempre que o JPanel precisa ser resenhado.
     * @param g Graphics componente de java.awt
     */

    // Método para fazer a imagem de fundo.
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Faz a imagem de fundo.
        if (imagemFundo != null) {
            g.drawImage(imagemFundo, 0, 0, getWidth(), getHeight(), null);
        } else {
            setBackground(Color.WHITE);
        }

        // Desenha os recursos.
        this.recursos.forEach(recurso -> recurso.desenhar(g, this));
        // Desenha os personagens.
        this.personagens.forEach(personagem -> personagem.desenhar(g, this));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
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