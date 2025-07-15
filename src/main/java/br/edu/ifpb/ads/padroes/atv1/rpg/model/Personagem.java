package br.edu.ifpb.ads.padroes.atv1.rpg.model;

import java.util.Arrays;

/**
 * classe base para Personagem - suporta Prototype
 */
public class Personagem implements Cloneable {
    
    private String nome;
    private String raca;
    private String classe;
    private int forca;
    private int inteligencia;
    private int agilidade;
    private int vida;
    private int mana;
    private Arma arma;
    private Armadura armadura;
    private String[] habilidades;
    
    public Personagem() {}
    
    public Personagem(String nome, String raca, String classe, int forca,
                      int inteligencia, int agilidade, int vida, int mana,
                      Arma arma, Armadura armadura, String[] habilidades) {
        this.nome = nome;
        this.raca = raca;
        this.classe = classe;
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.agilidade = agilidade;
        this.vida = vida;
        this.mana = mana;
        this.arma = arma;
        this.armadura = armadura;
        this.habilidades = habilidades != null ? habilidades.clone() : null;
    }
    
    @Override
    public Personagem clone() {
        try {
            Personagem cloned = (Personagem) super.clone();
            cloned.arma = this.arma != null ? this.arma.clone() : null;
            cloned.armadura = this.armadura != null ? this.armadura.clone() : null;
            cloned.habilidades = this.habilidades != null ? this.habilidades.clone() : null;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar personagem", e);
        }
    }
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    
    public String getClasse() { return classe; }
    public void setClasse(String classe) { this.classe = classe; }
    
    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }
    
    public int getInteligencia() { return inteligencia; }
    public void setInteligencia(int inteligencia) { this.inteligencia = inteligencia; }
    
    public int getAgilidade() { return agilidade; }
    public void setAgilidade(int agilidade) { this.agilidade = agilidade; }
    
    public int getVida() { return vida; }
    public void setVida(int vida) { this.vida = vida; }
    
    public int getMana() { return mana; }
    public void setMana(int mana) { this.mana = mana; }
    
    public Arma getArma() { return arma; }
    public void setArma(Arma arma) { this.arma = arma; }
    
    public Armadura getArmadura() { return armadura; }
    public void setArmadura(Armadura armadura) { this.armadura = armadura; }
    
    public String[] getHabilidades() { return habilidades; }
    public void setHabilidades(String[] habilidades) { 
        this.habilidades = habilidades != null ? habilidades.clone() : null; 
    }
    
    @Override
    public String toString() {
        return String.format("%s - %s %s (F:%d, I:%d, A:%d, V:%d, M:%d) - Arma: %s - Armadura: %s - Habilidades: %s",
                nome, raca, classe, forca, inteligencia, agilidade, vida, mana,
                arma != null ? arma.getNome() : "Nenhuma",
                armadura != null ? armadura.getNome() : "Nenhuma",
                habilidades != null ? Arrays.toString(habilidades) : "[]");
    }
}
