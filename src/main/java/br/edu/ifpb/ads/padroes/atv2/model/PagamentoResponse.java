package br.edu.ifpb.ads.padroes.atv2.model;

import java.time.LocalDateTime;

/**
 * Resposta do processamento de pagamento
 */
public class PagamentoResponse {
    
    public enum StatusPagamento {
        APROVADO, RECUSADO, PENDENTE, ERRO
    }
    
    private String idTransacao;
    private String idGateway;
    private StatusPagamento status;
    private String mensagem;
    private String codigoResposta;
    private LocalDateTime dataProcessamento;
    private String nomeGateway;
    
    public PagamentoResponse() {
        this.dataProcessamento = LocalDateTime.now();
    }
    
    public PagamentoResponse(String idTransacao, String idGateway, StatusPagamento status, 
                           String mensagem, String codigoResposta, String nomeGateway) {
        this.idTransacao = idTransacao;
        this.idGateway = idGateway;
        this.status = status;
        this.mensagem = mensagem;
        this.codigoResposta = codigoResposta;
        this.nomeGateway = nomeGateway;
        this.dataProcessamento = LocalDateTime.now();
    }
    
    // Getters e Setters
    public String getIdTransacao() { return idTransacao; }
    public void setIdTransacao(String idTransacao) { this.idTransacao = idTransacao; }
    
    public String getIdGateway() { return idGateway; }
    public void setIdGateway(String idGateway) { this.idGateway = idGateway; }
    
    public StatusPagamento getStatus() { return status; }
    public void setStatus(StatusPagamento status) { this.status = status; }
    
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    
    public String getCodigoResposta() { return codigoResposta; }
    public void setCodigoResposta(String codigoResposta) { this.codigoResposta = codigoResposta; }
    
    public LocalDateTime getDataProcessamento() { return dataProcessamento; }
    public void setDataProcessamento(LocalDateTime dataProcessamento) { this.dataProcessamento = dataProcessamento; }
    
    public String getNomeGateway() { return nomeGateway; }
    public void setNomeGateway(String nomeGateway) { this.nomeGateway = nomeGateway; }
    
    public boolean isAprovado() {
        return StatusPagamento.APROVADO.equals(status);
    }
    
    @Override
    public String toString() {
        return String.format("PagamentoResponse{id='%s', gateway='%s', status=%s, mensagem='%s', data=%s}", 
                           idTransacao, nomeGateway, status, mensagem, dataProcessamento);
    }
}
