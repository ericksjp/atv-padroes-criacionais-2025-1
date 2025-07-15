package br.edu.ifpb.ads.padroes.atv1.rpg.factory.equipment;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Arma;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Armadura;

/**
 * factory de equipamentos para orcs
 */
public class OrcEquipmentFactory extends EquipmentFactory {
    
    @Override
    public Arma criarEspadaGuerreiro() {
        return new Arma("Machado de Guerra", 30, "Machado");
    }
    
    @Override
    public Arma criarCajadoMago() {
        return new Arma("Cajado Tribal", 12, "Cajado");
    }
    
    @Override
    public Arma criarArcoArqueiro() {
        return new Arma("Arco de Osso", 24, "Arco");
    }
    
    @Override
    public Armadura criarArmaduraGuerreiro() {
        return new Armadura("Armadura Brutal", 25, "Pesada");
    }
    
    @Override
    public Armadura criarArmaduraMago() {
        return new Armadura("Vestes Xamânicas", 6, "Leve");
    }
    
    @Override
    public Armadura criarArmaduraArqueiro() {
        return new Armadura("Couro de Besta", 16, "Média");
    }
}
