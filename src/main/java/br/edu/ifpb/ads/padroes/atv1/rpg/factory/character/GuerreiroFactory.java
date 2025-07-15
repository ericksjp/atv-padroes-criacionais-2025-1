package br.edu.ifpb.ads.padroes.atv1.rpg.factory.character;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Personagem;

/**
 * factory específica para criação de Guerreiros
 */
public class GuerreiroFactory extends PersonagemFactory {
    
    public GuerreiroFactory(String raca) {
        super(raca);
    }
    
    @Override
    public Personagem criarPersonagem(String nome, String raca) {
        Personagem guerreiro = new Personagem();
        guerreiro.setNome(nome);
        guerreiro.setRaca(raca);
        guerreiro.setClasse("Guerreiro");
        
        guerreiro.setForca(15);
        guerreiro.setInteligencia(8);
        guerreiro.setAgilidade(10);
        guerreiro.setVida(120);
        guerreiro.setMana(30);
        
        configurarAtributosRaca(guerreiro, raca);
        
        guerreiro.setArma(equipmentFactory.criarEspadaGuerreiro());
        guerreiro.setArmadura(equipmentFactory.criarArmaduraGuerreiro());
        
        String[] habilidades = obterHabilidadesGuerreiro(raca);
        guerreiro.setHabilidades(habilidades);
        
        return guerreiro;
    }
    
    private String[] obterHabilidadesGuerreiro(String raca) {
        switch (raca.toLowerCase()) {
            case "humano":
                return new String[]{"Investida", "Bloqueio"};
            case "elfo":
                return new String[]{"Dança das Lâminas", "Agilidade Élfica"};
            case "orc":
                return new String[]{"Fúria", "Pancada Devastadora"};
            default:
                return new String[]{"Ataque Básico"};
        }
    }
}
