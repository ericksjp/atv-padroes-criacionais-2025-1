package br.edu.ifpb.ads.padroes.atv1.rpg.model;

/**
 * classe Armadura - suporta Prototype
 */
public class Armadura implements Cloneable {
    
    private String nome;
    private int defesa;
    private String tipo;
    
    public Armadura(String nome, int defesa, String tipo) {
        this.nome = nome;
        this.defesa = defesa;
        this.tipo = tipo;
    }
    
    @Override
    public Armadura clone() {
        try {
            return (Armadura) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar armadura", e);
        }
    }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - Defesa: %d", nome, tipo, defesa);
    }
}
