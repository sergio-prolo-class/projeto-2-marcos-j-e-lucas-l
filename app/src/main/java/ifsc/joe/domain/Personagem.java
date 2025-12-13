package ifsc.joe.domain;

// Essa classe foi implementada para ter herança e centralizar os atributos e métodos comum em todos os personagens;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import ifsc.joe.config.ConfiguracaoJogo;
import ifsc.joe.resources.ImageCache;
import ifsc.joe.domain.interfaces.Guerreiro;

public abstract class Personagem {

    protected Image imagemNormal;
    protected Image imagemAtacando;
    protected String nomeImagem;

    private static final ImageCache recursoManager = ImageCache.getInstancia();

    // classe com todos os valores;
    // todas as classes podem usar sem precisar instancia-la;
    public static final ConfiguracaoJogo config = new ConfiguracaoJogo();

    protected int posX, posY;
    protected boolean atacando;
    protected Image icone;
    protected int vida;
    protected int vidaMaxima;
    protected int velocidade;

    public Personagem(int x, int y, String nomeImagem, int vidaMaxima, int v) {
        this.posX = x;
        this.posY = y;
        this.nomeImagem = nomeImagem;
        this.icone = this.carregarImagem(nomeImagem);
        this.atacando = false;
        this.vidaMaxima = vidaMaxima;
        this.vida = vidaMaxima;
        this.velocidade = v;

        carregarImagens();
    }

    protected void carregarImagens() {
        System.out.println("Carregando imagens para: "+ nomeImagem);

        this.imagemNormal = recursoManager.get(nomeImagem);
        this.imagemAtacando = recursoManager.get(nomeImagem + "2");

        // validações
        if( imagemNormal == null) {
            System.err.println("Imagem normal não carregada: "+ nomeImagem);
        }

        if (imagemAtacando == null) {
            System.err.println("Imagem de ataque não carregada: " + nomeImagem + "2");
        }
        //recursoManager.exibirEstatisticasCache();
    }

    // Método para construir a barra de vida.
    private void barraVida(Graphics g, int x, int y) {

        double porcentagemVida = (double) vida / vidaMaxima;

        Color corBarra = null;

        if (porcentagemVida > 0.75) {
            corBarra = Color.GREEN;
        } else if (porcentagemVida > 0.25) {
            corBarra = Color.YELLOW;
        } else {
            corBarra = Color.RED;
        }

        int larguraBarra = 40;
        int alturaBarra = 5;
        int larguraVida = (int) (larguraBarra * porcentagemVida);

        // Barra preta de borda
        g.setColor(Color.BLACK);
        g.fillRect(x, y - alturaBarra - 2, larguraBarra, alturaBarra);

        // Barra verde de vida.
        g.setColor(corBarra);
        g.fillRect(x, y - alturaBarra - 2, larguraVida, alturaBarra);

        // Borda branca.
        g.setColor(Color.WHITE);
        g.drawRect(x, y - alturaBarra - 2, larguraBarra, alturaBarra);
    }

    public void desenhar(Graphics g, JPanel painel) {

        Image imagemAtual = atacando ? imagemAtacando : imagemNormal;

        //this.icone = this.carregarImagem(nomeImagem + (atacando ? "2" : ""));
        //g.drawImage(this.icone, this.posX, this.posY, painel);

        if (imagemAtual == null) {
            imagemAtual = imagemNormal;
        }

        if (imagemAtual != null){
            g.drawImage(imagemAtual, this.posX, this.posY, painel);
        } else {
            desenharPlaceholder(g);
        }

        barraVida(g, posX, posY);
        desenharAlcanceAtaque(g);
    }

    // Método para desenhar o círculo de alcance.
    private void desenharAlcanceAtaque(Graphics g) {
        if (this instanceof Guerreiro) {
            Guerreiro guerreiro = (Guerreiro) this;
            int alcance = guerreiro.getAlcanceAtaque();

            // Converte para Graphics2D para fazer a transparência.
            Graphics2D g2d = (Graphics2D) g;

            // Salva a cor original.
            Color corOriginal = g2d.getColor();

            // Colocar transparência na cor.
            Color vermelhoTransparente = new Color(255, 0, 0, 50);
            g2d.setColor(vermelhoTransparente);

            // Cálculo do centro do personagem.
            Image img = imagemNormal != null ? imagemNormal : imagemAtacando;
            int larguraImg = img != null ? img.getWidth(null) : 50;
            int alturaImg = img != null ? img.getHeight(null) : 50;

            int centroX = posX + (larguraImg / 2);
            int centroY = posY + (alturaImg / 2);

            // Desenho do círculo de alcance.
            g2d.fillOval(
                    centroX - alcance,
                    centroY - alcance,
                    alcance * 2,
                    alcance * 2
            );

            g2d.setColor(corOriginal);
        }
    }

    private void desenharPlaceholder(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(posX, posY, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(posX, posY, 50, 50);
        g.drawString("?", posX + 20, posY + 30);
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        final int ALTURA_BARRA_RECURSOS = 40;

        switch (direcao) {
            case CIMA     -> this.posY -= velocidade;
            case BAIXO    -> this.posY += velocidade;
            case ESQUERDA -> this.posX -= velocidade;
            case DIREITA  -> this.posX += velocidade;
        }

        Image img = imagemNormal != null ? imagemNormal : imagemAtacando;
        int larguraImg = img != null ? img.getWidth(null) : 50;
        int alturaImg = img != null ? img.getHeight(null) : 50;

        this.posX = Math.min(Math.max(0, this.posX), maxLargura - larguraImg);
        // Limita o movimento para não atingir a barra de recursos.
        this.posY = Math.min(
                Math.max(ALTURA_BARRA_RECURSOS, this.posY),
                maxAltura - alturaImg
        );

    }

    public void alterAtaque() {
        this.atacando = !this.atacando;
    }

    public void receberDano (int dano) {
        this.vida = Math.max(0, this.vida - dano);
        System.out.println(nomeImagem + " recebeu " + dano + " de dano. Vida: " + vida);
    }

    public boolean estaMorto() {
        return vida <= 0;
    }

    public double distanciaAlvo(Personagem alvo) {
        int dx = this.posX - alvo.posX;
        int dy = this.posY - alvo.posY;
        return Math.sqrt(dx * dx + dy * dy);
    }

    protected Image carregarImagem(String imagem) {
        return new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("./"+imagem+".png")
        )).getImage();
    }

    // getters:
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public boolean estaAtacando() {
        return atacando;
    }

    public int getVida() {
        return vida;
    }

}