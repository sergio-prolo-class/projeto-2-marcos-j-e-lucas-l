// Arquivo: ifsc/joe/factory/PersonagemFactory.java
package ifsc.joe.factory;

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

    // ===== MÉTODOS PRIVADOS DE CRIAÇÃO =====

    private static Aldeao criarAldeao(int x, int y) {
        return new Aldeao(x, y);
    }

    private static Arqueiro criarArqueiro(int x, int y) {
        return new Arqueiro(x, y);
    }

    private static Cavaleiro criarCavaleiro(int x, int y) {
        return new Cavaleiro(x, y);
    }

    
     // Obtém informações sobre um tipo de personagem sem criar uma instância
    
    public static String obterInformacoes(TipoPersonagem tipo) {
        if (!tipo.isCriavel()) {
            return tipo.getDescricao();
        }

        return switch (tipo) {
            case ALDEAO -> String.format(
                "%s - Velocidade: %d, Coleta: %d",
                tipo.getDescricao(),
                config.getAldeaoVelocidade(),
                config.getAldeaoCapacidadeColeta()
            );
            case ARQUEIRO -> String.format(
                "%s - Velocidade: %d, Ataque: %d, Alcance: %d",
                tipo.getDescricao(),
                config.getArqueiroVelocidade(),
                config.getArqueiroForcaAtaque(),
                config.getArqueiroAlcance()
            );
            case CAVALEIRO -> String.format(
                "%s - Velocidade: %d, Ataque: %d, Montaria: %s",
                tipo.getDescricao(),
                config.getCavaleiroVelocidade(),
                config.getCavaleiroForcaAtaque(),
                config.getCavaleiroTipoMontaria()
            );
            default -> tipo.getDescricao();
        };
    }

    
     // Lista todos os tipos criáveis de personagens
     
    public static TipoPersonagem[] getTiposCriaveis() {
        return new TipoPersonagem[]{
            TipoPersonagem.ALDEAO,
            TipoPersonagem.ARQUEIRO,
            TipoPersonagem.CAVALEIRO
        };
    }


     // Valida se um tipo pode ser criado
     
    public static boolean podeSerCriado(TipoPersonagem tipo) {
        return tipo != null && tipo.isCriavel();
    }
}


// package ifsc.joe.factory;


// import ifsc.joe.domain.Personagem;
// import ifsc.joe.domain.impl.Aldeao;
// import ifsc.joe.domain.impl.Arqueiro;
// import ifsc.joe.domain.impl.Cavaleiro;
// import ifsc.joe.enums.TipoPersonagem;


// public class PersonagemFactory {

//     private PersonagemFactory() {
//         throw new AssertionError("Classe factory não deve ser instanciada");
//     }

//     public static Personagem criar(TipoPersonagem tipo, int x, int y) {

//         if (tipo == null) {
//             throw new IllegalArgumentException("Tipo de personagem nãp pode ser nullo");
//         }


//         if (!tipo.isCriavel()) {
//             throw new IllergalArgumentException(
//                 String.format("Tipo '%s' não pode ser criado diretamente", tipo.getNome());
//             );
//         }


//         return switch (tipo) {
//             case ALDEAO -> criarAldeao(x, y);
//             case ARQUEIRO -> criarArqueiro(x, y);
//             case CAVALEIRO -> criarCavaleiro(x, y);
//             default -> throw new IllegalArgumentException(
//                 "Tipo de personagem não suportado:" + tipo
//             );
//         };
//     }



// }



