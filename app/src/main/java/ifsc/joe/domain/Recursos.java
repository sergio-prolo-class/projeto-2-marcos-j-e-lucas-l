package ifsc.joe.domain;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class Recursos {

    protected static final int QUANTIDADE = 10;
    protected int posX, posY;
    protected Image icone;
    protected String nomeImagem;

    // Construtor da superclasse Recursos, que as classes Madeira, Ouro e Trigo herdarão.
    public Recursos(int x, int y, String nomeImagem) {
        this.posX = x;
        this.posY = y;
        this.nomeImagem = nomeImagem;
        this.icone = this.carregarImagem(nomeImagem);
    }

    // Método igual a superclasse Personagem para carregar imagens.
    private Image carregarImagem(String imagem) {
        return new ImageIcon(Objects.requireNonNull(
                getClass().getClassLoader().getResource("./"+imagem+".png")
        )).getImage();
    }

    // Método para desenhar os recursos na tela.
    public void desenhar(Graphics g, JPanel painel) {
        g.drawImage(this.icone, this.posX, this.posY, painel);
    }

    public static int getQuantidade() {
        return QUANTIDADE;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

}
