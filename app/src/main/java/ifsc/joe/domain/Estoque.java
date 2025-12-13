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

    // Método para o estoque de recursos.
    public boolean temRecursos(int madeira, int ouro, int trigo) {
        return this.madeira >= madeira &&
                this.ouro >= ouro &&
                this.trigo >= trigo;
    }

    // Método para o consumo de recursos.
    public boolean consumirRecursos(int madeira, int ouro, int trigo) {
        if (temRecursos(madeira, ouro, trigo)) {
            this.madeira -= madeira;
            this.ouro -= ouro;
            this.trigo -= trigo;
            return true;
        }
        return false;
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
