package ifsc.joe.factory;

import ifsc.joe.domain.Estoque;
import ifsc.joe.domain.Personagem;
import ifsc.joe.domain.impl.Aldeao;
import ifsc.joe.domain.impl.Arqueiro;
import ifsc.joe.domain.impl.Cavaleiro;
import ifsc.joe.enums.TipoPersonagem;

import ifsc.joe.config.ConfiguracaoJogo;

 //Factory responsável pela criação centralizada de personagens.
public class PersonagemFactory {

    private static final ConfiguracaoJogo config = new ConfiguracaoJogo();

    // Construtor privado para evitar instanciação
    private PersonagemFactory() {
        throw new AssertionError("Classe factory não deve ser instanciada");
    }

     public static Personagem criarComCusto(TipoPersonagem tipo, int x, int y, Estoque estoque) {
         if (tipo == null) {
             throw new IllegalArgumentException("Tipo de personagem não pode ser nulo");
         }

         if (! tipo.isCriavel()) {
             throw new IllegalArgumentException(
                     String.format("Tipo '%s' não pode ser criado diretamente", tipo.getNome())
             );
         }

         // Define o custo de cada personagem
         int custoMadeira = 0;
         int custoOuro = 0;
         int custoTrigo = 0;

         switch (tipo) {
             case ALDEAO:
                 // Aldeão não tem custo
                 break;
             case ARQUEIRO:
                 custoTrigo = 1;
                 break;
             case CAVALEIRO:
                 custoOuro = 1;
                 break;
         }

         // Verifica se tem recursos suficientes
         if (! estoque.temRecursos(custoMadeira, custoOuro, custoTrigo)) {
             throw new IllegalStateException(
                     String.format("Recursos insuficientes para criar %s!  Necessário: %d madeira, %d ouro, %d trigo",
                             tipo.getNome(), custoMadeira, custoOuro, custoTrigo)
             );
         }

         // Consome os recursos
         estoque.consumirRecursos(custoMadeira, custoOuro, custoTrigo);

         // Cria o personagem
         return criar(tipo, x, y);
     }

     // Cria um personagem do tipo especificado nas coordenadas fornecidas.

    public static Personagem criar(TipoPersonagem tipo, int x, int y) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de personagem não pode ser nulo");
        }

        if (!tipo.isCriavel()) {
            throw new IllegalArgumentException(
                String.format("Tipo '%s' não pode ser criado diretamente", tipo.getNome())
            );
        }
        return switch (tipo) {
            case ALDEAO -> criarAldeao(x, y);
            case ARQUEIRO -> criarArqueiro(x, y);
            case CAVALEIRO -> criarCavaleiro(x, y);
            default -> throw new IllegalArgumentException(
                "Tipo de personagem não suportado: " + tipo
            );
        };
    }

     // Sobrecarga: cria personagem em posição padrão (0, 0)
    public static Personagem criar(TipoPersonagem tipo) {
        return criar(tipo, 0, 0);
    }

     //Cria um personagem aleatório entre os tipos disponíveis
    public static Personagem criarAleatorio(int x, int y) {
        TipoPersonagem[] tiposCriaveis = {
            TipoPersonagem.ALDEAO,
            TipoPersonagem.ARQUEIRO,
            TipoPersonagem.CAVALEIRO
        };
        
        int indiceAleatorio = (int) (Math.random() * tiposCriaveis.length);
        return criar(tiposCriaveis[indiceAleatorio], x, y);
    }

    //  MÉTODOS PRIVADOS DE CRIAÇÃO, da própria classe
    private static Aldeao criarAldeao(int x, int y) {
        return new Aldeao(x, y);
    }

    private static Arqueiro criarArqueiro(int x, int y) {
        return new Arqueiro(x, y);
    }

    private static Cavaleiro criarCavaleiro(int x, int y) {
        return new Cavaleiro(x, y);
    }

     // Valida se um tipo pode ser criado
    public static boolean podeSerCriado(TipoPersonagem tipo) {
        return tipo != null && tipo.isCriavel();
    }
}