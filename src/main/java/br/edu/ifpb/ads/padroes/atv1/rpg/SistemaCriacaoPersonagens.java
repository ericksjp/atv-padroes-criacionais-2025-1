package br.edu.ifpb.ads.padroes.atv1.rpg;

import br.edu.ifpb.ads.padroes.atv1.rpg.builder.PersonagemBuilder;
import br.edu.ifpb.ads.padroes.atv1.rpg.config.ConfiguracaoJogo;
import br.edu.ifpb.ads.padroes.atv1.rpg.factory.character.PersonagemFactory;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Personagem;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Arma;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Armadura;
import br.edu.ifpb.ads.padroes.atv1.rpg.prototype.PersonagemPrototypeRegistry;

public class SistemaCriacaoPersonagens {
    
    public static void main(String[] args) {
        System.out.println("=== Sistema de Criação de Personagens RPG - Versão Refatorada ===\n");
        
        demonstrarSingleton();
        
        demonstrarFactoryMethod();
        
        demonstrarAbstractFactory();
        
        demonstrarBuilder();
        
        demonstrarPrototype();
        
        System.out.println("\n=== Demonstração Concluída ===");
    }
    
    private static void demonstrarSingleton() {
        System.out.println("1. padrao singleton - Configuração do Jogo");
        System.out.println("==========================================");
        
        ConfiguracaoJogo config = ConfiguracaoJogo.getInstance();
        System.out.println("Configuração inicial: Dificuldade " + config.getNivelDificuldade() + 
                          ", Versão " + config.getVersaoJogo());
        
        config.setNivelDificuldade(3);
        config.setModoDebug(true);
        
        ConfiguracaoJogo config2 = ConfiguracaoJogo.getInstance();
        System.out.println("Segunda instância: Dificuldade " + config2.getNivelDificuldade());
        System.out.println("Mesma instancia: " + (config == config2));
        System.out.println();
    }
    
    private static void demonstrarFactoryMethod() {
        System.out.println("2. padrão factory method - Criação por Classe");
        System.out.println("==============================================");
        
        // criando um factory para cada classe de personagem
        PersonagemFactory guerreiroFactory = PersonagemFactory.criarFactory("Guerreiro", "Humano");
        PersonagemFactory magoFactory = PersonagemFactory.criarFactory("Mago", "Elfo");
        PersonagemFactory arqueiroFactory = PersonagemFactory.criarFactory("Arqueiro", "Orc");

        Personagem guerreiro = guerreiroFactory.criarPersonagem("Aragorn", "Humano");
        System.out.println("Guerreiro criado: " + guerreiro);

        Personagem mago = magoFactory.criarPersonagem("Elrond", "Elfo");
        System.out.println("Mago criado: " + mago);
        
        Personagem arqueiro = arqueiroFactory.criarPersonagem("Ugluk", "Orc");
        System.out.println("Arqueiro criado: " + arqueiro);
        System.out.println();
    }
    
    private static void demonstrarAbstractFactory() {
        System.out.println("3. padrão abstract factory - Equipamentos por Raça");
        System.out.println("===================================================");
        
        // diferentes raças tem equipamentos diferentes
        
        PersonagemFactory humanoGuerreiro = PersonagemFactory.criarFactory("Guerreiro", "Humano");
        Personagem guerreiroHumano = humanoGuerreiro.criarPersonagem("João", "Humano");
        
        PersonagemFactory elfoGuerreiro = PersonagemFactory.criarFactory("Guerreiro", "Elfo");
        Personagem guerreiroElfo = elfoGuerreiro.criarPersonagem("Thranduil", "Elfo");
        
        PersonagemFactory orcGuerreiro = PersonagemFactory.criarFactory("Guerreiro", "Orc");
        Personagem guerreiroOrc = orcGuerreiro.criarPersonagem("Azog", "Orc");
        
        System.out.println("Guerreiro Humano - Arma: " + guerreiroHumano.getArma());
        System.out.println("Guerreiro Elfo - Arma: " + guerreiroElfo.getArma());
        System.out.println("Guerreiro Orc - Arma: " + guerreiroOrc.getArma());
        System.out.println();
    }
    
    private static void demonstrarBuilder() {
        System.out.println("4. padrão builder - Construção Complexa");
        System.out.println("========================================");
        
        Personagem personagemSimples = new PersonagemBuilder()
                .criarBase("Gimli", "Humano", "Guerreiro")
                .build();
        System.out.println("Personagem simples: " + personagemSimples);
        
        Personagem personagemCustomizado = new PersonagemBuilder()
                .criarBase("Boromir", "Humano", "Guerreiro")
                .comForca(18)
                .comVida(140)
                .adicionarHabilidade("Grito de Guerra")
                .aplicarModificadorDificuldade()
                .build();
        System.out.println("Personagem customizado: " + personagemCustomizado);
        
        Personagem personagemEpico = new PersonagemBuilder()
                .criarBase("Thorin", "Humano", "Guerreiro")
                .versaoEpica()
                .build();
        System.out.println("Personagem épico: " + personagemEpico);
        
        Personagem personagemCompleto = new PersonagemBuilder()
                .comNome("Merlin")
                .comRaca("Humano")
                .comClasse("Mago")
                .comAtributos(8, 25, 12, 90, 250)
                .comArma(new Arma("Cajado do Tempo", 50, "Cajado Lendário"))
                .comArmadura(new Armadura("Mantos da Eternidade", 30, "Mágica"))
                .comHabilidades("Controle do Tempo", "Visão do Futuro", "Imortalidade")
                .build();
        
        System.out.println("Personagem totalmente customizado: " + personagemCompleto);
        System.out.println();
    }
    
    private static void demonstrarPrototype() {
        System.out.println("5. padrão prototype - Clonagem de Personagens");
        System.out.println("==============================================");
        
        PersonagemPrototypeRegistry registry = PersonagemPrototypeRegistry.getInstance();
        
        System.out.println("Protótipos básicos disponíveis:");
        String[] prototipos = registry.listarPrototipos();
        for (String prototipo : prototipos) {
            if (prototipo.matches("\\w+\\w+")) {
                System.out.println("- " + prototipo);
            }
        }
        
        System.out.println("\nProtótipos especiais disponíveis:");
        String[] prototiposEspeciais = registry.listarPrototiposEspeciais();
        for (String prototipo : prototiposEspeciais) {
            System.out.println("- " + prototipo);
        }
        
        Personagem clone1 = registry.criarPersonagemDePrototipo("Elfo", "Mago", "Galadriel");
        System.out.println("\nClone de protótipo Elfo Mago: " + clone1);
        
        Personagem clone2 = registry.criarPersonagemDePrototipo("Elfo", "Mago", "Radagast");
        System.out.println("Outro clone do mesmo protótipo: " + clone2);
        
        System.out.println("São instâncias diferentes? " + (clone1 != clone2));
        
        Personagem lendario = registry.criarPersonagemDePrototipoEspecial("HumanoGuerreiroLendario", "Excalibur");
        System.out.println("Personagem de protótipo especial: " + lendario);
        
        Personagem original = registry.obterPrototipo("Orc", "Guerreiro");
        original.setNome("Lurtz Original");
        
        Personagem cloneManual = original.clone();
        cloneManual.setNome("Lurtz Clone");
        cloneManual.setForca(cloneManual.getForca() + 5);
        
        System.out.println("Original: " + original);
        System.out.println("Clone modificado: " + cloneManual);
        System.out.println();
    }
}
