package br.edu.ifpb.ads.padroes.atv1.rpg.builder;

import br.edu.ifpb.ads.padroes.atv1.rpg.model.Personagem;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Arma;
import br.edu.ifpb.ads.padroes.atv1.rpg.model.Armadura;
import br.edu.ifpb.ads.padroes.atv1.rpg.factory.character.PersonagemFactory;
import br.edu.ifpb.ads.padroes.atv1.rpg.config.ConfiguracaoJogo;

/**
 * padrao builder - Classe para construção de Personagens
 */
public class PersonagemBuilder {
    
    private Personagem personagem;
    
    public PersonagemBuilder() {
        this.personagem = new Personagem();
    }
    
    public PersonagemBuilder criarBase(String nome, String raca, String classe) {
        PersonagemFactory factory = PersonagemFactory.criarFactory(classe, raca);
        this.personagem = factory.criarPersonagem(nome, raca);
        return this;
    }
    
    public PersonagemBuilder comNome(String nome) {
        this.personagem.setNome(nome);
        return this;
    }
    
    public PersonagemBuilder comRaca(String raca) {
        this.personagem.setRaca(raca);
        return this;
    }
    
    public PersonagemBuilder comClasse(String classe) {
        this.personagem.setClasse(classe);
        return this;
    }
    
    public PersonagemBuilder comAtributos(int forca, int inteligencia, int agilidade, int vida, int mana) {
        this.personagem.setForca(forca);
        this.personagem.setInteligencia(inteligencia);
        this.personagem.setAgilidade(agilidade);
        this.personagem.setVida(vida);
        this.personagem.setMana(mana);
        return this;
    }
    
    public PersonagemBuilder comForca(int forca) {
        this.personagem.setForca(forca);
        return this;
    }
    
    public PersonagemBuilder comInteligencia(int inteligencia) {
        this.personagem.setInteligencia(inteligencia);
        return this;
    }
    
    public PersonagemBuilder comAgilidade(int agilidade) {
        this.personagem.setAgilidade(agilidade);
        return this;
    }
    
    public PersonagemBuilder comVida(int vida) {
        this.personagem.setVida(vida);
        return this;
    }
    
    public PersonagemBuilder comMana(int mana) {
        this.personagem.setMana(mana);
        return this;
    }
    
    public PersonagemBuilder comArma(Arma arma) {
        this.personagem.setArma(arma);
        return this;
    }
    
    public PersonagemBuilder comArmadura(Armadura armadura) {
        this.personagem.setArmadura(armadura);
        return this;
    }
    
    public PersonagemBuilder comHabilidades(String... habilidades) {
        this.personagem.setHabilidades(habilidades);
        return this;
    }
    
    public PersonagemBuilder adicionarHabilidade(String habilidade) {
        String[] habilidadesAtuais = this.personagem.getHabilidades();
        if (habilidadesAtuais == null) {
            this.personagem.setHabilidades(new String[]{habilidade});
        } else {
            // copy array
            String[] novasHabilidades = new String[habilidadesAtuais.length + 1];
            System.arraycopy(habilidadesAtuais, 0, novasHabilidades, 0, habilidadesAtuais.length);
            novasHabilidades[habilidadesAtuais.length] = habilidade;
            this.personagem.setHabilidades(novasHabilidades);
        }
        return this;
    }
    
    public PersonagemBuilder aplicarModificadorDificuldade() {
        ConfiguracaoJogo config = ConfiguracaoJogo.getInstance();
        int multiplicador = config.getNivelDificuldade();
        
        if (multiplicador > 1) {
            this.personagem.setVida(this.personagem.getVida() + (10 * (multiplicador - 1)));
            this.personagem.setMana(this.personagem.getMana() + (5 * (multiplicador - 1)));
        }
        
        return this;
    }
    
    // personagem apelao - status aumentados
    public PersonagemBuilder versaoEpica() {
        this.personagem.setNome(this.personagem.getNome() + " o Lendário");
        
        this.personagem.setForca(this.personagem.getForca() + 5);
        this.personagem.setInteligencia(this.personagem.getInteligencia() + 5);
        this.personagem.setAgilidade(this.personagem.getAgilidade() + 5);
        this.personagem.setVida(this.personagem.getVida() + 50);
        this.personagem.setMana(this.personagem.getMana() + 30);
        
        if (this.personagem.getArma() != null) {
            Arma armaEpica = this.personagem.getArma().clone();
            armaEpica.setNome("Épico " + armaEpica.getNome());
            armaEpica.setDano(armaEpica.getDano() + 10);
            this.personagem.setArma(armaEpica);
        }
        
        if (this.personagem.getArmadura() != null) {
            Armadura armaduraEpica = this.personagem.getArmadura().clone();
            armaduraEpica.setNome("Épica " + armaduraEpica.getNome());
            armaduraEpica.setDefesa(armaduraEpica.getDefesa() + 10);
            this.personagem.setArmadura(armaduraEpica);
        }
        
        adicionarHabilidade("Poder Lendário");
        
        return this;
    }
    
    public Personagem build() {
        if (personagem.getNome() == null || personagem.getNome().trim().isEmpty()) {
            // easter egg
            personagem.setNome("Diogo Personagem");
        }

        if (personagem.getRaca() == null) {
            personagem.setRaca("Humano");
        }
        
        if (personagem.getClasse() == null) {
            personagem.setClasse("Guerreiro");
        }
        
        return personagem;
    }
    
    public PersonagemBuilder reset() {
        this.personagem = new Personagem();
        return this;
    }
}
