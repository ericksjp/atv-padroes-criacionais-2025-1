package br.edu.ifpb.ads.padroes.atv1.rpg.factory.character;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Personagem;

/**
 * factory específica para criação de Magos
 */
public class MagoFactory extends PersonagemFactory {
    
    public MagoFactory(String raca) {
        super(raca);
    }
    
    @Override
    public Personagem criarPersonagem(String nome, String raca) {
        Personagem mago = new Personagem();
        mago.setNome(nome);
        mago.setRaca(raca);
        mago.setClasse("Mago");
        
        mago.setForca(6);
        mago.setInteligencia(18);
        mago.setAgilidade(8);
        mago.setVida(80);
        mago.setMana(150);
        
        configurarAtributosRaca(mago, raca);
        
        mago.setArma(equipmentFactory.criarCajadoMago());
        mago.setArmadura(equipmentFactory.criarArmaduraMago());
        
        String[] habilidades = obterHabilidadesMago(raca);
        mago.setHabilidades(habilidades);
        
        return mago;
    }
    
    private String[] obterHabilidadesMago(String raca) {
        switch (raca.toLowerCase()) {
            case "humano":
                return new String[]{"Bola de Fogo", "Cura"};
            case "elfo":
                return new String[]{"Magia da Natureza", "Teleporte"};
            case "orc":
                return new String[]{"Magia Sombria", "Invocação"};
            default:
                return new String[]{"Míssil Mágico"};
        }
    }
}
