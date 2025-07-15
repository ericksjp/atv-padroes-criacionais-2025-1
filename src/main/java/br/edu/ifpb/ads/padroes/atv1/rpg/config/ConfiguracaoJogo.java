package br.edu.ifpb.ads.padroes.atv1.rpg.config;

/**
 * padrão singleton - Configuração única do jogo
 */
public class ConfiguracaoJogo {
    
    // volatile garante que as mudanças nesse singletown sejas refletidas em outras threads
    private static volatile ConfiguracaoJogo instance;
    private int nivelDificuldade;
    private boolean modoDebug;
    private String versaoJogo;
    
    private ConfiguracaoJogo() {
        this.nivelDificuldade = 1;
        this.modoDebug = false;
        this.versaoJogo = "2.0";
    }
    
    public static ConfiguracaoJogo getInstance() {
        if (instance == null) {
            // deadlock para evitar inconsistencia
            synchronized (ConfiguracaoJogo.class) {
                if (instance == null) {
                    instance = new ConfiguracaoJogo();
                }
            }
        }
        return instance;
    }
    
    public int getNivelDificuldade() {
        return nivelDificuldade;
    }
    
    public void setNivelDificuldade(int nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }
    
    public boolean isModoDebug() {
        return modoDebug;
    }
    
    public void setModoDebug(boolean modoDebug) {
        this.modoDebug = modoDebug;
    }
    
    public String getVersaoJogo() {
        return versaoJogo;
    }
}
