package br.edu.ifpb.ads.padroes.atv1.rpg.factory.character;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Personagem;

/**
 * factory especifico para arqueiros
 */
public class ArqueiroFactory extends PersonagemFactory {
    
    public ArqueiroFactory(String raca) {
        super(raca);
    }
    
    @Override
    public Personagem criarPersonagem(String nome, String raca) {
        Personagem arqueiro = new Personagem();
        arqueiro.setNome(nome);
        arqueiro.setRaca(raca);
        arqueiro.setClasse("Arqueiro");
        
        // Atributos base do arqueiro
        arqueiro.setForca(10);
        arqueiro.setInteligencia(12);
        arqueiro.setAgilidade(16);
        arqueiro.setVida(100);
        arqueiro.setMana(70);
        
        configurarAtributosRaca(arqueiro, raca);
        
        arqueiro.setArma(equipmentFactory.criarArcoArqueiro());
        arqueiro.setArmadura(equipmentFactory.criarArmaduraArqueiro());
        
        // Habilidades específicas por raça
        String[] habilidades = obterHabilidadesArqueiro(raca);
        arqueiro.setHabilidades(habilidades);
        
        return arqueiro;
    }
    
    private String[] obterHabilidadesArqueiro(String raca) {
        switch (raca.toLowerCase()) {
            case "humano":
                return new String[]{"Tiro Certeiro", "Chuva de Flechas"};
            case "elfo":
                return new String[]{"Tiro Múltiplo", "Camuflagem"};
            case "orc":
                return new String[]{"Tiro Brutal", "Intimidação"};
            default:
                return new String[]{"Tiro Básico"};
        }
    }
}
