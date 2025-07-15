package br.edu.ifpb.ads.padroes.atv1.rpg.factory.equipment;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Arma;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Armadura;

/**
 * padrão abstract factory - interface para fabrica de equipamentos
 */
public abstract class EquipmentFactory {
    
    public abstract Arma criarEspadaGuerreiro();
    public abstract Arma criarCajadoMago();
    public abstract Arma criarArcoArqueiro();
    
    public abstract Armadura criarArmaduraGuerreiro();
    public abstract Armadura criarArmaduraMago();
    public abstract Armadura criarArmaduraArqueiro();
    
    // factory method para determinar qual factory usar
    public static EquipmentFactory criarFactory(String raca) {
        switch (raca.trim().toLowerCase()) {
            case "humano":
                return new HumanoEquipmentFactory();
            case "elfo":
                return new ElfoEquipmentFactory();
            case "orc":
                return new OrcEquipmentFactory();
            default:
                throw new IllegalArgumentException("Raça não suportada: " + raca);
        }
    }
}
