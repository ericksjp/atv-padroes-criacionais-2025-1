package br.edu.ifpb.ads.padroes.atv2.gateway.impl;

import br.edu.ifpb.ads.padroes.atv2.gateway.PagamentoGateway;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoRequest;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoResponse;
import br.edu.ifpb.ads.padroes.atv2.sdk.pagseguro.PagSeguroSDK;

/**
 * Implementação do gateway PagSeguro
 * Integra com o SDK mock do PagSeguro
 */
public class PagSeguroGateway implements PagamentoGateway {
    
    private final PagSeguroSDK pagSeguroSDK;
    
    public PagSeguroGateway(PagSeguroSDK pagSeguroSDK) {
        this.pagSeguroSDK = pagSeguroSDK;
    }
    
    @Override
    public PagamentoResponse processarPagamento(PagamentoRequest request) {
        try {
            System.out.println("\n=== Processando pagamento via PagSeguro ===");
            
            // Converte para formato do PagSeguro SDK
            PagSeguroSDK.PagSeguroTransactionRequest pagSeguroRequest = 
                new PagSeguroSDK.PagSeguroTransactionRequest(
                    request.getIdTransacao(),
                    request.getValor(),
                    request.getEmailCliente(),
                    request.getCartaoTitular()
                );
            
            // Chama SDK do PagSeguro
            PagSeguroSDK.PagSeguroTransactionResult pagSeguroResult = 
                pagSeguroSDK.createTransaction(pagSeguroRequest);
            
            // Converte resposta do PagSeguro para formato padrão
            PagamentoResponse response = new PagamentoResponse();
            response.setIdTransacao(request.getIdTransacao());
            response.setIdGateway(pagSeguroResult.getCode());
            response.setNomeGateway(getNomeGateway());
            response.setCodigoResposta(String.valueOf(pagSeguroResult.getStatus()));
            response.setMensagem(pagSeguroResult.getStatusMessage());
            
            // Mapeia status do PagSeguro
            // Status PagSeguro: 1=Aguardando, 2=Em análise, 3=Paga, 4=Disponível, 5=Em disputa, 6=Devolvida, 7=Cancelada
            switch (pagSeguroResult.getStatus()) {
                case 3: // Paga
                case 4: // Disponível
                    response.setStatus(PagamentoResponse.StatusPagamento.APROVADO);
                    break;
                case 7: // Cancelada
                    response.setStatus(PagamentoResponse.StatusPagamento.RECUSADO);
                    break;
                case 1: // Aguardando
                case 2: // Em análise
                    response.setStatus(PagamentoResponse.StatusPagamento.PENDENTE);
                    break;
                default:
                    response.setStatus(PagamentoResponse.StatusPagamento.ERRO);
            }
            
            return response;
            
        } catch (Exception e) {
            System.err.println("Erro ao processar pagamento no PagSeguro: " + e.getMessage());
            return criarRespostaErro(request, e.getMessage());
        }
    }
    
    @Override
    public String getNomeGateway() {
        return "PagSeguro";
    }
    
    @Override
    public boolean isDisponivel() {
        return pagSeguroSDK != null && pagSeguroSDK.isAuthenticated();
    }
    
    private PagamentoResponse criarRespostaErro(PagamentoRequest request, String mensagemErro) {
        PagamentoResponse response = new PagamentoResponse();
        response.setIdTransacao(request.getIdTransacao());
        response.setNomeGateway(getNomeGateway());
        response.setStatus(PagamentoResponse.StatusPagamento.ERRO);
        response.setMensagem("Erro no processamento: " + mensagemErro);
        response.setCodigoResposta("ERROR");
        return response;
    }
}
