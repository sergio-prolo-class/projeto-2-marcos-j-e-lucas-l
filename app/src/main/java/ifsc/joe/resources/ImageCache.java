package ifsc.joe.resources;


import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Chache simplificado e thead-safe de imagens
public class ImageCache {

    private static ImageCache instancia;
    private static final Map<String, Image> cache = new ConcurrentHashMap<>();
    private int tentativasCarregamento = 0;
    private int hitCache = 0;

    // construtor privado
    private ImageCache() {}

    // Obtém uma imagem do cache ou carrega se necessário
    public Image get(String nome) {
        hitCache++;
        tentativasCarregamento++;
        return cache.computeIfAbsent(nome, ImageCache::carregar);
    }

    // Carregar uma imagem do disco
    private static Image carregar(String nome) {
        try {
            var url = ImageCache.class.getClassLoader()
                    .getResource("./" + nome + ".png");


            if (url == null) {
                System.out.println("Imagem não encontrada: "+ nome);
                return null;
            }

            System.out.println("Carregando imagem: "+ nome);
            return new ImageIcon(url).getImage();

        }catch (Exception e) {
            System.err.println("Erro ao carregar: "+ nome);
            return null;
        }
    }

    // limpar cache
    public static void clear() {
        cache.clear();
    }

    // Tamanho do cache
    public static int size() {
        return cache.size();
    }


    public static synchronized ImageCache getInstancia() {
        if (instancia == null) {
            instancia = new ImageCache();
        }

        return instancia;
    }

    public void preCarregarImagens(String... nomesImagens) {
        System.out.println(" Pré-carregando "+ nomesImagens.length + "imagens...");


        for (String nome: nomesImagens){
            get(nome);
        }

        System.out.println("Pré-carregamento concluído!");
    }

    public int getTamanhoCache() {
        return cache.size();
    }

    public double getTaxaHitCache() {
        if (tentativasCarregamento == 0) return 0.0;
        return (hitCache * 100.0) / tentativasCarregamento;
    }

    // Melhorar esse método mais tarde
    public void exibirEstatisticasCache() {
        System.out.println("\n [warning] ESTATISTICAS DO cache");
        System.out.println("Tamanho do cache: "+ getTamanhoCache() + " imagens");
        System.out.println("Tentativas de carregamento: "+ tentativasCarregamento);
        System.out.println("Cache HIT: " + hitCache);
        System.out.println("Taxa de acerto: "+ String.format("%.2f", getTaxaHitCache()) + "%");
        System.out.println("Imagens em cache: "+ cache.keySet());
        System.out.println("-----------");
    }
}
