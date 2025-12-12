package ifsc.joe;

import ifsc.joe.ui.JanelaJogo;
import javax.swing.*;
import ifsc.joe.resources.ImageCache;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JanelaJogo janela = new JanelaJogo();
            janela.exibir();

            exibirEstatisticasCache();
        });
    }

    private static void preCarregarRecursos() {

        System.out.println(" Iniciando pré-carregamento de recursos...");

        ImageCache manager = ImageCache.getInstancia();

        manager.preCarregarImagens(
            "aldeao",
            "arqueiro",
            "arqueiro2",
            "cavaleiro",
            "cavaleiro2"
        );

        System.out.println("Recuross pré-carregaods com sucesso");
    }

    private static void exibirEstatisticasCache() {

        new Thread(() -> {
            try{
                Thread.sleep(5000);
                ImageCache.getInstancia().exibirEstatisticasCache();

            }catch (InterruptedException e) {
                e.printStackTrace();
            }   
        }).start();
    }
}