package br.edu.ifpb.ads.padroes.atv2.gateway.impl;

import br.edu.ifpb.ads.padroes.atv2.gateway.PagamentoGateway;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoRequest;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoResponse;
import br.edu.ifpb.ads.padroes.atv2.sdk.stripe.StripeSDK;

import java.math.BigDecimal;

/**
 * Implementação do gateway Stripe
 */
public class StripeGateway implements PagamentoGateway {
    
    private final StripeSDK stripeSDK;
    
    public StripeGateway(StripeSDK stripeSDK) {
        this.stripeSDK = stripeSDK;
    }
    
    @Override
    public PagamentoResponse processarPagamento(PagamentoRequest request) {
        try {
            System.out.println("\n=== Processando pagamento via Stripe ===");
            
            long amountInCents = request.getValor().multiply(new BigDecimal("100")).longValue();
            
            String cardToken = "tok_" + request.getCartaoNumero();
            
            StripeSDK.StripeChargeRequest stripeRequest = new StripeSDK.StripeChargeRequest(
                amountInCents,
                request.getMoeda().toLowerCase(),
                cardToken,
                request.getDescricao()
            );
            
            StripeSDK.StripeChargeResult stripeResult = stripeSDK.createCharge(stripeRequest);
            
            PagamentoResponse response = new PagamentoResponse();
            response.setIdTransacao(request.getIdTransacao());
            response.setIdGateway(stripeResult.getId());
            response.setNomeGateway(getNomeGateway());
            response.setCodigoResposta(stripeResult.getStatus());
            
            if (stripeResult.isPaid() && "succeeded".equals(stripeResult.getStatus())) {
                response.setStatus(PagamentoResponse.StatusPagamento.APROVADO);
                response.setMensagem("Pagamento aprovado com sucesso");
            } else {
                response.setStatus(PagamentoResponse.StatusPagamento.RECUSADO);
                response.setMensagem(stripeResult.getFailureMessage() != null ? 
                                   stripeResult.getFailureMessage() : "Pagamento recusado");
            }
            
            return response;
            
        } catch (Exception e) {
            System.err.println("Erro ao processar pagamento no Stripe: " + e.getMessage());
            return criarRespostaErro(request, e.getMessage());
        }
    }
    
    @Override
    public String getNomeGateway() {
        return "Stripe";
    }
    
    @Override
    public boolean isDisponivel() {
        return stripeSDK != null && stripeSDK.isValidCredentials();
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
