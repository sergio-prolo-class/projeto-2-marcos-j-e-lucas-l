package ifsc.joe.domain;

public class Estoque {

    // Classe responsável pelo estoque de recursos
    private int madeira;
    private int ouro;
    private int trigo;

    public Estoque() {
        this.madeira = 0;
        this.ouro = 0;
        this.trigo = 0;
    }

    public void adicionarMadeira(int quantidade) {
        this.madeira += quantidade;
    }

    public void adicionarOuro(int quantidade) {
        this.ouro += quantidade;
    }

    public void adicionarTrigo(int quantidade) {
        this.trigo += quantidade;
    }

    public int getMadeira() {
        return madeira;
    }

    public int getOuro() {
        return ouro;
    }

    public int getTrigo() {
        return trigo;
    }

    // Método para resetar recursos.
    public void resetar() {
        this.madeira = 0;
        this.ouro = 0;
        this.trigo = 0;
    }
}
