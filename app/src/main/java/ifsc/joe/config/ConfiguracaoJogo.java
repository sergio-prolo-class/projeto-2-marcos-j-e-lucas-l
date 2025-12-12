// Arquivo: ifsc/joe/config/ConfiguracaoJogo.java
package ifsc.joe.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe responsável por carregar e fornecer acesso às configurações do jogo.
 * Carrega valores do arquivo joe.properties.
 */
public class ConfiguracaoJogo {

    private static boolean ready = true;

    private static ConfiguracaoJogo instancia;
    private final Properties propriedades;

    public ConfiguracaoJogo() {
        this.propriedades = new Properties();
        carregarPropriedades();
    }

    /**
     * Singleton - garante uma única instância
     */
    public static ConfiguracaoJogo getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracaoJogo();
        }
        return instancia;
    }

    // Carrega o arquivo joe.properties do classpath
    private void carregarPropriedades() {
        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("joe.properties")) {
            
            if (input == null) {
                System.err.println("Arquivo joe.properties não encontrado! Usando valores padrão.");
                carregarValoresPadrao();
                return;
            }
            
            propriedades.load(input);
            
            if(ready){
            System.out.println("Configurações carregadas com sucesso de joe.properties");
            ready = false;
            }

            
        } catch (IOException e) {
            System.err.println("Erro ao carregar joe.properties: " + e.getMessage());
            carregarValoresPadrao();
        }
    }

    
     //Valores padrão caso o arquivo não seja encontrado
    private void carregarValoresPadrao() {
        // Configurações visuais
        propriedades.setProperty("tela.largura", "800");
        propriedades.setProperty("tela.altura", "600");
        propriedades.setProperty("personagem.padding", "50");
        
        // Aldeão
        propriedades.setProperty("aldeao.vida.inicial", "100");
        propriedades.setProperty("aldeao.velocidade", "10");
        propriedades.setProperty("aldeao.capacidade.coleta", "10");
        
        // Arqueiro
        propriedades.setProperty("arqueiro.vida.inicial", "80");
        propriedades.setProperty("arqueiro.velocidade", "12");
        propriedades.setProperty("arqueiro.forca.ataque", "15");
        propriedades.setProperty("arqueiro.alcance", "100");
        propriedades.setProperty("arqueiro.defesa","5");
        
        // Cavaleiro
        propriedades.setProperty("cavaleiro.vida.inicial", "150");
        propriedades.setProperty("cavaleiro.velocidade", "15");
        propriedades.setProperty("cavaleiro.forca.ataque", "25");
        propriedades.setProperty("cavaleiro.alcance", "50");
        propriedades.setProperty("cavaleiro.defesa", "10");
    }

    // ===== MÉTODOS GETTER TIPADOS =====

    public int getInt(String chave) {
        return Integer.parseInt(propriedades.getProperty(chave, "0"));
    }

    public String getString(String chave) {
        return propriedades.getProperty(chave, "");
    }

    public double getDouble(String chave) {
        return Double.parseDouble(propriedades.getProperty(chave, "0.0"));
    }

    // ===== MÉTODOS DE CONVENIÊNCIA PARA CONFIGURAÇÕES ESPECÍFICAS =====

    // Configurações Visuais
    public int getTelaLargura() {
        return getInt("tela.largura");
    }

    public int getTelaAltura() {
        return getInt("tela.altura");
    }

    public int getPersonagemPadding() {
        return getInt("personagem.padding");
    }

    public String getJogoTitulo() {
        return getString("jogo.titulo");
    }

    // Aldeão
    public int getAldeaoVidaInicial() {
        return getInt("aldeao.vida.inicial");
    }

    public int getAldeaoVelocidade() {
        return getInt("aldeao.velocidade");
    }

    public int getAldeaoCapacidadeColeta() {
        return getInt("aldeao.capacidade.coleta");
    }

    public String getAldeaoImagemNome() {
        return getString("aldeao.imagem.nome");
    }

    // Arqueiro
    public int getArqueiroVidaInicial() {
        return getInt("arqueiro.vida.inicial");
    }

    public int getArqueiroVelocidade() {
        return getInt("arqueiro.velocidade");
    }

    public int getArqueiroForcaAtaque() {
        return getInt("arqueiro.forca.ataque");
    }

    public int getArqueiroAlcance() {
        return getInt("arqueiro.alcance");
    }

    public String getArqueiroImagemNome() {
        return getString("arqueiro.imagem.nome");
    }

    public int getArqueiroDefesa(){
        return getInt("arqueiro.defesa");
    }

    // Cavaleiro
    public int getCavaleiroVidaInicial() {
        return getInt("cavaleiro.vida.inicial");
    }

    public int getCavaleiroVelocidade() {
        return getInt("cavaleiro.velocidade");
    }

    public int getCavaleiroForcaAtaque() {
        return getInt("cavaleiro.forca.ataque");
    }

    public int getCavaleiroAlcance() {
        return getInt("cavaleiro.alcance");
    }

    public int getCavaleiroDefesa(){
        return getInt("cavaleiro.defesa");
    }

    public String getCavaleiroTipoMontaria() {
        return getString("cavaleiro.tipo.montaria");
    }

    public String getCavaleiroImagemNome() {
        return getString("cavaleiro.imagem.nome");
    }

    /**
     * Recarrega as propriedades do arquivo (útil para hot-reload)
     */
    public void recarregar() {
        propriedades.clear();
        carregarPropriedades();
    }
}
