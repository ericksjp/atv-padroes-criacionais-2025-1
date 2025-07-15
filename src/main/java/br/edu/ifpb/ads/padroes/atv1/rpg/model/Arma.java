package br.edu.ifpb.ads.padroes.atv1.rpg.model;

/**
 * classe Arma - suporta Prototype
 */
public class Arma implements Cloneable {
    
    private String nome;
    private int dano;
    private String tipo;
    
    public Arma(String nome, int dano, String tipo) {
        this.nome = nome;
        this.dano = dano;
        this.tipo = tipo;
    }
    
    @Override
    public Arma clone() {
        try {
            return (Arma) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar arma", e);
        }
    }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getDano() { return dano; }
    public void setDano(int dano) { this.dano = dano; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - Dano: %d", nome, tipo, dano);
    }
}
