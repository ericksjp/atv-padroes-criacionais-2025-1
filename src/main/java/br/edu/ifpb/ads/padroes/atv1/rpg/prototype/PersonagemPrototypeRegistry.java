package br.edu.ifpb.ads.padroes.atv1.rpg.prototype;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Personagem;
import br.edu.ifpb.ads.padroes.atv1.rpg.factory.character.PersonagemFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * padrão Prototype - singleton de prototipos de personagens
 */
public class PersonagemPrototypeRegistry {
    
    private static volatile PersonagemPrototypeRegistry instance;
    private Map<String, Personagem> prototipos;
    
    private PersonagemPrototypeRegistry() {
        this.prototipos = new HashMap<>();
        inicializarPrototipos();
    }
    
    public static PersonagemPrototypeRegistry getInstance() {
        if (instance == null) {
            synchronized (PersonagemPrototypeRegistry.class) {
                if (instance == null) {
                    instance = new PersonagemPrototypeRegistry();
                }
            }
        }
        return instance;
    }
    
    private void inicializarPrototipos() {
        String[] racas = {"Humano", "Elfo", "Orc"};
        String[] classes = {"Guerreiro", "Mago", "Arqueiro"};
        
        for (String raca : racas) {
            for (String classe : classes) {
                String chave = criarChave(raca, classe);
                PersonagemFactory factory = PersonagemFactory.criarFactory(classe, raca);
                Personagem prototipo = factory.criarPersonagem("Protótipo", raca);
                prototipos.put(chave, prototipo);
            }
        }
        
        criarPrototiposEspeciais();
    }
    
    private void criarPrototiposEspeciais() {
        PersonagemFactory factory = PersonagemFactory.criarFactory("Guerreiro", "Humano");
        Personagem guerreiroLendario = factory.criarPersonagem("Arthur", "Humano");
        guerreiroLendario.setForca(20);
        guerreiroLendario.setVida(150);
        guerreiroLendario.setHabilidades(new String[]{"Investida", "Bloqueio", "Liderança", "Excalibur"});
        prototipos.put("HumanoGuerreiroLendario", guerreiroLendario);
        
        factory = PersonagemFactory.criarFactory("Mago", "Elfo");
        Personagem arquimago = factory.criarPersonagem("Gandalf", "Elfo");
        arquimago.setInteligencia(25);
        arquimago.setMana(200);
        arquimago.setHabilidades(new String[]{"Magia da Natureza", "Teleporte", "Meteoro", "Tempo"});
        prototipos.put("ElfoMagoArquimago", arquimago);
        
        // Arqueiro Elfo Ranger
        factory = PersonagemFactory.criarFactory("Arqueiro", "Elfo");
        Personagem ranger = factory.criarPersonagem("Legolas", "Elfo");
        ranger.setAgilidade(25);
        ranger.setHabilidades(new String[]{"Tiro Múltiplo", "Camuflagem", "Rastreamento", "Tiro Impossível"});
        prototipos.put("ElfoArqueiroRanger", ranger);
    }
    
    public Personagem obterPrototipo(String raca, String classe) {
        String chave = criarChave(raca, classe);
        Personagem prototipo = prototipos.get(chave);
        
        if (prototipo == null) {
            throw new IllegalArgumentException("Protótipo não encontrado para: " + raca + " " + classe);
        }
        
        return prototipo.clone();
    }
    
    public Personagem obterPrototipoEspecial(String nomePrototipo) {
        Personagem prototipo = prototipos.get(nomePrototipo);
        
        if (prototipo == null) {
            throw new IllegalArgumentException("Protótipo especial não encontrado: " + nomePrototipo);
        }
        
        return prototipo.clone();
    }
    
    public void adicionarPrototipo(String chave, Personagem prototipo) {
        prototipos.put(chave, prototipo.clone());
    }
    
    public void removerPrototipo(String chave) {
        prototipos.remove(chave);
    }
    
    public String[] listarPrototipos() {
        return prototipos.keySet().toArray(new String[0]);
    }
    
    public String[] listarPrototiposEspeciais() {
        return prototipos.keySet().stream()
                .filter(chave -> !chave.matches("\\w+\\w+")) // Filtra chaves que não seguem o padrão RacaClasse
                .toArray(String[]::new);
    }
    
    private String criarChave(String raca, String classe) {
        return raca + classe;
    }
    
    // Criar personagem personalizado a partir de protótipo
    public Personagem criarPersonagemDePrototipo(String raca, String classe, String nome) {
        Personagem personagem = obterPrototipo(raca, classe);
        personagem.setNome(nome);
        return personagem;
    }
    
    public Personagem criarPersonagemDePrototipoEspecial(String nomePrototipo, String nomePersonagem) {
        Personagem personagem = obterPrototipoEspecial(nomePrototipo);
        personagem.setNome(nomePersonagem);
        return personagem;
    }
}
