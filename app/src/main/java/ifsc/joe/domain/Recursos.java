package ifsc.joe.domain;

import ifsc.joe.enums.TipoRecurso;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public abstract class Recursos {

    protected static final int QUANTIDADE = 5;
    protected int posX, posY;
    protected Image icone;
    protected String nomeImagem;
    protected TipoRecurso tipo;

    // Construtor da superclasse Recursos, que as classes Madeira, Ouro e Trigo herdarão.
    public Recursos(int x, int y, String nomeImagem, TipoRecurso tipo) {
        this.posX = x;
        this.posY = y;
        this.nomeImagem = nomeImagem;
        this.icone = this.carregarImagem(nomeImagem);
        this.tipo = tipo;
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

    public TipoRecurso getTipo() {
        return tipo;
    }

    // Método para ver se o personagem está próximo.
    public boolean verificaProximidade(int x, int y, int raio) {
        int dx = this.posX - x;
        int dy = this.posY - y;
        double distancia = Math.sqrt(dx * dx + dy * dy);
        return distancia <= raio;
    }

}
