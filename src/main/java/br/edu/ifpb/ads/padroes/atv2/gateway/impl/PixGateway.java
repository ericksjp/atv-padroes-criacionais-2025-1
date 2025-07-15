package br.edu.ifpb.ads.padroes.atv2.gateway.impl;

import br.edu.ifpb.ads.padroes.atv2.gateway.PagamentoGateway;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoRequest;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoResponse;

import java.util.Random;

/**
 * Implementação do gateway PIX
 * Demonstra a extensibilidade do sistema para novos provedores
 * Simula pagamento instantâneo via PIX
 */
public class PixGateway implements PagamentoGateway {
    
    private final String chaveInstituicao;
    private final boolean ativo;
    
    public PixGateway(String chaveInstituicao, boolean ativo) {
        this.chaveInstituicao = chaveInstituicao;
        this.ativo = ativo;
    }
    
    @Override
    public PagamentoResponse processarPagamento(PagamentoRequest request) {
        try {
            System.out.println("\n=== Processando pagamento via PIX ===");
            System.out.println("[PIX] Valor: R$ " + request.getValor());
            System.out.println("[PIX] Chave: " + request.getEmailCliente());
            
            // Simula aprovação baseada em valor limite
            boolean aprovado = request.getValor().doubleValue() <= 5000.0; // Limite PIX
            
            PagamentoResponse response = new PagamentoResponse();
            response.setIdTransacao(request.getIdTransacao());
            response.setIdGateway("PIX_" + gerarCodigoTransacao());
            response.setNomeGateway(getNomeGateway());
            
            if (aprovado) {
                response.setStatus(PagamentoResponse.StatusPagamento.APROVADO);
                response.setMensagem("Pagamento PIX aprovado instantaneamente");
                response.setCodigoResposta("APPROVED");
            } else {
                response.setStatus(PagamentoResponse.StatusPagamento.RECUSADO);
                response.setMensagem("Valor excede limite PIX de R$ 5.000,00");
                response.setCodigoResposta("LIMIT_EXCEEDED");
            }
            
            System.out.println("[PIX] Status: " + response.getStatus());
            return response;
            
        } catch (Exception e) {
            System.err.println("Erro ao processar pagamento PIX: " + e.getMessage());
            return criarRespostaErro(request, e.getMessage());
        }
    }
    
    @Override
    public String getNomeGateway() {
        return "PIX";
    }
    
    @Override
    public boolean isDisponivel() {
        return ativo && chaveInstituicao != null && !chaveInstituicao.trim().isEmpty();
    }
    
    private String gerarCodigoTransacao() {
        return String.valueOf(System.currentTimeMillis() + new Random().nextInt(1000));
    }
    
    private PagamentoResponse criarRespostaErro(PagamentoRequest request, String mensagemErro) {
        PagamentoResponse response = new PagamentoResponse();
        response.setIdTransacao(request.getIdTransacao());
        response.setNomeGateway(getNomeGateway());
        response.setStatus(PagamentoResponse.StatusPagamento.ERRO);
        response.setMensagem("Erro no processamento PIX: " + mensagemErro);
        response.setCodigoResposta("ERROR");
        return response;
    }
}
