package ifsc.joe.enums;


public enum TipoPersonagem {

    TODOS("Todos", "Seleciona todos os personagens"),
    ALDEAO("Aldeão", "Unidade coletora básica"),
    ARQUEIRO("Arqueiro","Guerreiro de longo alcance"),
    CAVALEIRO("Cavaleiro", "Guerreiro montado poderiso");

    private final String nome;
    private final String descricao;

    TipoPersonagem(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isCriavel() {
        return this != TODOS;
    }

    @Override
    public String toString() {
        return nome;
    }
}
