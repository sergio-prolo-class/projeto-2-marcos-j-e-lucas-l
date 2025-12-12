package ifsc.joe.domain;

// Essa classe foi implementada para ter herança e centralizar os atributos e métodos comum em todos os personagens;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import ifsc.joe.config.ConfiguracaoJogo;
import ifsc.joe.resources.ImageCache;

// Em suma ela é uma cópia da implementação de aldeão feita pelo professor;
// Foi feito poucas alterações visando futuras implementações nelas;

public abstract class Personagem {
// Decidir não documentar linha por linha, pois ela é uma copia da aldeão, que está ao final toda escrita como comentarios;

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
    protected int velocidade;

    // Valor padrão definido;
    protected static final int VIDATOTAL = 100;

    public Personagem(int x, int y, String nomeImagem, int vida, int v) {
        this.posX = x;
        this.posY = y;
        this.nomeImagem = nomeImagem;
        this.icone = this.carregarImagem(nomeImagem);
        this.atacando = false;
        this.vida = vida;
        this.velocidade = v;

        carregarImagens();
    }

    private void carregarImagens() {
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
    private void barraVida(Graphics g) {
        int larguraBarra = 40;
        int alturaBarra = 5;
        int offsetY = -10;

        // Barra vermelha de borda
        g.setColor(Color.RED);
        g.fillRect(posX, posY + offsetY, larguraBarra, alturaBarra);

        // Barra verde de vida.
        int larguraVida = (int) ((double) vida / VIDATOTAL  * larguraBarra);
        g.setColor(Color.GREEN);
        g.fillRect(posX, posY + offsetY, larguraVida, alturaBarra);

        // Borda preta.
        g.setColor(Color.BLACK);
        g.drawRect(posX, posY + offsetY, larguraBarra, alturaBarra);
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
        }else {
            desenharPlaceholder(g);
        }

        barraVida(g);
    }

    private void desenharPlaceholder(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(posX, posY, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(posX, posY, 50, 50);
        g.drawString("?", posX + 20, posY + 30);
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        //int velocidade = getVelocidadeBase();

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
        this.posY = Math.min(Math.max(0, this.posY), maxAltura - alturaImg);

        //this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
        //this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
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

   // protected int getVelocidadeBase() {
     //   return 10;
    //}

    private Image carregarImagem(String imagem) {
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