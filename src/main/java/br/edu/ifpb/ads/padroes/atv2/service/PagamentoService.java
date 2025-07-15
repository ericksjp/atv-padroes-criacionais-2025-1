package br.edu.ifpb.ads.padroes.atv2.service;

import br.edu.ifpb.ads.padroes.atv2.gateway.PagamentoGateway;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoRequest;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoResponse;

public class PagamentoService {
    
    private final PagamentoGateway pagamentoGateway;
    
    public PagamentoService(PagamentoGateway pagamentoGateway) {
        if (pagamentoGateway == null) {
            throw new IllegalArgumentException("PagamentoGateway não pode ser null");
        }
        this.pagamentoGateway = pagamentoGateway;
    }
    
    public PagamentoResponse pagar(PagamentoRequest request) {
        System.out.println("\n[PagamentoService] Iniciando processamento do pagamento...");
        System.out.println("[PagamentoService] Gateway selecionado: " + pagamentoGateway.getNomeGateway());
        System.out.println("[PagamentoService] Transação ID: " + request.getIdTransacao());
        
        if (!pagamentoGateway.isDisponivel()) {
            return criarRespostaIndisponivel(request);
        }
        
        PagamentoResponse response = pagamentoGateway.processarPagamento(request);
        
        System.out.println("[PagamentoService] Pagamento processado com status: " + response.getStatus());
        System.out.println("[PagamentoService] Mensagem: " + response.getMensagem());
        
        return response;
    }
    
    public String getGatewayInfo() {
        return String.format("Gateway: %s | Disponível: %s", 
                           pagamentoGateway.getNomeGateway(), 
                           pagamentoGateway.isDisponivel());
    }
    
    public boolean isReady() {
        return pagamentoGateway != null && pagamentoGateway.isDisponivel();
    }
    
    private PagamentoResponse criarRespostaIndisponivel(PagamentoRequest request) {
        PagamentoResponse response = new PagamentoResponse();
        response.setIdTransacao(request.getIdTransacao());
        response.setNomeGateway(pagamentoGateway.getNomeGateway());
        response.setStatus(PagamentoResponse.StatusPagamento.ERRO);
        response.setMensagem("Gateway de pagamento indisponível");
        response.setCodigoResposta("SERVICE_UNAVAILABLE");
        return response;
    }
}
