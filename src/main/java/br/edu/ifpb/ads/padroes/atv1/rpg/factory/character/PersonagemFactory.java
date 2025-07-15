package br.edu.ifpb.ads.padroes.atv1.rpg.factory.character;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Personagem;
import br.edu.ifpb.ads.padroes.atv1.rpg.factory.equipment.EquipmentFactory;

/**
 * padrao factory method - classe abstrata para criacao de personagens
 */
public abstract class PersonagemFactory {
    
    protected EquipmentFactory equipmentFactory;
    protected String raca;
    
    public PersonagemFactory(String raca) {
        this.equipmentFactory = EquipmentFactory.criarFactory(raca);
    }
    
    public abstract Personagem criarPersonagem(String nome, String raca);
    
    protected void configurarAtributosRaca(Personagem personagem, String raca) {
        switch (raca.toLowerCase()) {
            case "humano":
                break;
            case "elfo":
                personagem.setInteligencia(personagem.getInteligencia() + 2);
                personagem.setAgilidade(personagem.getAgilidade() + 2);
                personagem.setForca(Math.max(1, personagem.getForca() - 2));
                break;
            case "orc":
                personagem.setForca(personagem.getForca() + 3);
                personagem.setInteligencia(Math.max(1, personagem.getInteligencia() - 2));
                break;
        }
    }
    
    public static PersonagemFactory criarFactory(String classe, String raca) {
        switch (classe.toLowerCase()) {
            case "guerreiro":
                return new GuerreiroFactory(raca);
            case "mago":
                return new MagoFactory(raca);
            case "arqueiro":
                return new ArqueiroFactory(raca);
            default:
                throw new IllegalArgumentException("Classe não suportada: " + classe);
        }
    }
}
