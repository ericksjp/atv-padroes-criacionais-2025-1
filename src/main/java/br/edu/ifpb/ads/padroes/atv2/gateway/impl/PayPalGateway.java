package br.edu.ifpb.ads.padroes.atv2.gateway.impl;

import br.edu.ifpb.ads.padroes.atv2.gateway.PagamentoGateway;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoRequest;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoResponse;
import br.edu.ifpb.ads.padroes.atv2.sdk.paypal.PayPalSDK;

/**
 * Implementação do gateway PayPal
 * Integra com o SDK mock do PayPal
 */
public class PayPalGateway implements PagamentoGateway {
    
    private final PayPalSDK paypalSDK;
    
    public PayPalGateway(PayPalSDK paypalSDK) {
        this.paypalSDK = paypalSDK;
    }
    
    @Override
    public PagamentoResponse processarPagamento(PagamentoRequest request) {
        try {
            System.out.println("\n=== Processando pagamento via PayPal ===");
            
            PayPalSDK.PayPalPaymentRequest paypalRequest = new PayPalSDK.PayPalPaymentRequest(
                request.getValor(),
                request.getMoeda(),
                request.getEmailCliente(),
                request.getDescricao()
            );
            
            PayPalSDK.PayPalPaymentResult paypalResult = paypalSDK.createPayment(paypalRequest);
            
            PagamentoResponse response = new PagamentoResponse();
            response.setIdTransacao(request.getIdTransacao());
            response.setIdGateway(paypalResult.getPaymentId());
            response.setNomeGateway(getNomeGateway());
            response.setCodigoResposta(paypalResult.getState());
            response.setMensagem(paypalResult.getMessage());
            
            switch (paypalResult.getState().toLowerCase()) {
                case "approved":
                    response.setStatus(PagamentoResponse.StatusPagamento.APROVADO);
                    break;
                case "failed":
                    response.setStatus(PagamentoResponse.StatusPagamento.RECUSADO);
                    break;
                default:
                    response.setStatus(PagamentoResponse.StatusPagamento.PENDENTE);
            }
            
            return response;
            
        } catch (Exception e) {
            System.err.println("Erro ao processar pagamento no PayPal: " + e.getMessage());
            return criarRespostaErro(request, e.getMessage());
        }
    }
    
    @Override
    public String getNomeGateway() {
        return "PayPal";
    }
    
    @Override
    public boolean isDisponivel() {
        return paypalSDK != null && paypalSDK.isConnected();
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
