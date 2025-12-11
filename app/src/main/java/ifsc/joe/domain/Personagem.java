package ifsc.joe.domain;

// Essa classe foi implementada para ter herança e centralizar os atributos e métodos comum em todos os personagens;

import ifsc.joe.enums.Direcao;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import ifsc.joe.config.ConfiguracaoJogo;

// Em suma ela é uma cópia da implementação de aldeão feita pelo professor;
// Foi feito poucas alterações visando futuras implementações nelas;

public abstract class Personagem {
// Decidir não documentar linha por linha, pois ela é uma copia da aldeão, que está ao final toda escrita como comentarios;

    // classe com todos os valores;
    // todas as classes podem usar sem precisar instancia-la;
    public static final ConfiguracaoJogo config = new ConfiguracaoJogo();

    protected int posX, posY;
    protected boolean atacando;
    protected Image icone;
    protected String nomeImagem;
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
        this.icone = this.carregarImagem(nomeImagem + (atacando ? "2" : ""));
        g.drawImage(this.icone, this.posX, this.posY, painel);

        barraVida(g);
    }

    public void mover(Direcao direcao, int maxLargura, int maxAltura) {
        //int velocidade = getVelocidadeBase();

        switch (direcao) {
            case CIMA     -> this.posY -= velocidade;
            case BAIXO    -> this.posY += velocidade;
            case ESQUERDA -> this.posX -= velocidade;
            case DIREITA  -> this.posX += velocidade;
        }

        this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
        this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
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

// class que foi usada como base para implementar a personagem:
// Deixar ela como backup kkkk

// package ifsc.joe.domain.impl;

// import ifsc.joe.enums.Direcao;

// import javax.swing.*;
// import java.awt.*;
// import java.util.Objects;

// public class Aldeao {

//     public static final String NOME_IMAGEM = "aldeao";

//     private int posX, posY;
//     private boolean atacando;
//     private Image icone;

//     public Aldeao(int x, int y) {
//         this.icone = this.carregarImagem(NOME_IMAGEM);
//         this.posX = x;
//         this.posY = y;
//         this.atacando = false;
//     }

//     /**
//      * Desenhando o Aldeão, nas coordenadas X e Y, com a imagem 'icone'
//      * no JPanel 'pai'
//      *
//      * @param g objeto do JPanel que será usado para desenhar o Aldeão
//      */
//     public void desenhar(Graphics g, JPanel painel) {
//         // verificando qual imagem carregar
//         this.icone = this.carregarImagem(NOME_IMAGEM + (atacando ? "2" : ""));
//         // desenhando de fato a imagem no pai
//         g.drawImage(this.icone, this.posX, this.posY, painel);
//     }

//     /**
//      * Atualiza as coordenadas X e Y do personagem
//      *
//      * @param direcao indica a direcao a ir.
//      */
//     public void mover(Direcao direcao, int maxLargura, int maxAltura) {
//         switch (direcao) {
//             case CIMA     -> this.posY -= 10;
//             case BAIXO    -> this.posY += 10;
//             case ESQUERDA -> this.posX -= 10;
//             case DIREITA  -> this.posX += 10;
//         }

//         //Não deixa a imagem ser desenhada fora dos limites do JPanel pai
//         this.posX = Math.min(Math.max(0, this.posX), maxLargura - this.icone.getWidth(null));
//         this.posY = Math.min(Math.max(0, this.posY), maxAltura - this.icone.getHeight(null));
//     }


//     public void atacar() {
//         this.atacando = !this.atacando;
//     }

//     /**
//      * Metodo auxiliar para carregar uma imagem do disco
//      *
//      * @param imagem Caminho da imagem
//      * @return Retorna um objeto Image
//      */
//     private Image carregarImagem(String imagem) {
//         return new ImageIcon(Objects.requireNonNull(
//                 getClass().getClassLoader().getResource("./"+imagem+".png")
//         )).getImage();
//     }

// }