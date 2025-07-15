package br.edu.ifpb.ads.padroes.atv1.rpg.factory.equipment;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Arma;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Armadura;

/**
 * factory de equipamentos para humanos
 */
public class HumanoEquipmentFactory extends EquipmentFactory {

    @Override
    public Arma criarEspadaGuerreiro() {
        return new Arma("Espada de Ferro", 25, "Espada");
    }

    @Override
    public Arma criarCajadoMago() {
        return new Arma("Cajado Mágico", 15, "Cajado");
    }

    @Override
    public Arma criarArcoArqueiro() {
        return new Arma("Arco Élfico", 20, "Arco");
    }

    @Override
    public Armadura criarArmaduraGuerreiro() {
        return new Armadura("Armadura de Placas", 20, "Pesada");
    }

    @Override
    public Armadura criarArmaduraMago() {
        return new Armadura("Vestes Mágicas", 8, "Leve");
    }

    @Override
    public Armadura criarArmaduraArqueiro() {
        return new Armadura("Armadura de Couro", 12, "Média");
    }
}
