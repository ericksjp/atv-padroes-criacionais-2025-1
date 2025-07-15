package br.edu.ifpb.ads.padroes.atv2.model;

import java.math.BigDecimal;

public class PagamentoRequest {
    
    private String idTransacao;
    private BigDecimal valor;
    private String moeda;
    private String cartaoNumero;
    private String cartaoTitular;
    private String cartaoValidate;
    private String cartaoCvv;
    private String emailCliente;
    private String descricao;
    
    public PagamentoRequest() {}
    
    public PagamentoRequest(String idTransacao, BigDecimal valor, String moeda, 
                          String cartaoNumero, String cartaoTitular, String cartaoValidate, 
                          String cartaoCvv, String emailCliente, String descricao) {
        this.idTransacao = idTransacao;
        this.valor = valor;
        this.moeda = moeda;
        this.cartaoNumero = cartaoNumero;
        this.cartaoTitular = cartaoTitular;
        this.cartaoValidate = cartaoValidate;
        this.cartaoCvv = cartaoCvv;
        this.emailCliente = emailCliente;
        this.descricao = descricao;
    }
    
    // Getters e Setters
    public String getIdTransacao() { return idTransacao; }
    public void setIdTransacao(String idTransacao) { this.idTransacao = idTransacao; }
    
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    
    public String getMoeda() { return moeda; }
    public void setMoeda(String moeda) { this.moeda = moeda; }
    
    public String getCartaoNumero() { return cartaoNumero; }
    public void setCartaoNumero(String cartaoNumero) { this.cartaoNumero = cartaoNumero; }
    
    public String getCartaoTitular() { return cartaoTitular; }
    public void setCartaoTitular(String cartaoTitular) { this.cartaoTitular = cartaoTitular; }
    
    public String getCartaoValidate() { return cartaoValidate; }
    public void setCartaoValidate(String cartaoValidate) { this.cartaoValidate = cartaoValidate; }
    
    public String getCartaoCvv() { return cartaoCvv; }
    public void setCartaoCvv(String cartaoCvv) { this.cartaoCvv = cartaoCvv; }
    
    public String getEmailCliente() { return emailCliente; }
    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    @Override
    public String toString() {
        return String.format("PagamentoRequest{id='%s', valor=%s, moeda='%s', titular='%s', descricao='%s'}", 
                           idTransacao, valor, moeda, cartaoTitular, descricao);
    }
}
