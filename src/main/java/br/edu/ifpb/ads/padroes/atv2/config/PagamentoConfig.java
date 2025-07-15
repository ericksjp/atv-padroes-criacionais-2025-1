package br.edu.ifpb.ads.padroes.atv2.config;

import br.edu.ifpb.ads.padroes.atv2.gateway.PagamentoGateway;
import br.edu.ifpb.ads.padroes.atv2.gateway.impl.PayPalGateway;
import br.edu.ifpb.ads.padroes.atv2.gateway.impl.StripeGateway;
import br.edu.ifpb.ads.padroes.atv2.gateway.impl.PagSeguroGateway;
import br.edu.ifpb.ads.padroes.atv2.gateway.impl.PixGateway;
import br.edu.ifpb.ads.padroes.atv2.service.PagamentoService;
import br.edu.ifpb.ads.padroes.atv2.sdk.paypal.PayPalSDK;
import br.edu.ifpb.ads.padroes.atv2.sdk.stripe.StripeSDK;
import br.edu.ifpb.ads.padroes.atv2.sdk.pagseguro.PagSeguroSDK;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class PagamentoConfig {
    
    public static PagamentoService criarPagamentoServicePayPal() {
        MutablePicoContainer container = new DefaultPicoContainer();
        
        PayPalSDK paypalSDK = new PayPalSDK(
            "AQkquBDf1zctJOWGKWUEtKXm6qVhueUEMvXO_-MCI4DQtsI-8gPiVkHaYAQD",
            "ECYhM1vI0_keMhR5HkAHlQ3fFgXcFwkHI-3BIU",
            true
        );
        container.addComponent(PayPalSDK.class, paypalSDK);
        
        container.addComponent(PagamentoGateway.class, PayPalGateway.class);
        
        container.addComponent(PagamentoService.class);
        
        return container.getComponent(PagamentoService.class);
    }
    
    public static PagamentoService criarPagamentoServiceStripe() {
        MutablePicoContainer container = new DefaultPicoContainer();
        
        StripeSDK stripeSDK = new StripeSDK(
            "sk_test_4eC39HqLyjWDarjtT1zdp7dc",
            "pk_test_TYooMQauvdEDq54NiTphI7jx"
        );
        container.addComponent(StripeSDK.class, stripeSDK);
        
        container.addComponent(PagamentoGateway.class, StripeGateway.class);
        
        container.addComponent(PagamentoService.class);
        
        return container.getComponent(PagamentoService.class);
    }
    
    public static PagamentoService criarPagamentoServicePagSeguro() {
        MutablePicoContainer container = new DefaultPicoContainer();
        
        PagSeguroSDK pagSeguroSDK = new PagSeguroSDK(
            "sandbox@pagseguro.com.br",
            "AD742F1B0A4B4D14B2A8B3F7F9E1D2C3",
            true
        );
        container.addComponent(PagSeguroSDK.class, pagSeguroSDK);
        
        container.addComponent(PagamentoGateway.class, PagSeguroGateway.class);
        
        container.addComponent(PagamentoService.class);
        
        return container.getComponent(PagamentoService.class);
    }
    
    public static PagamentoService criarPagamentoServicePix() {
        MutablePicoContainer container = new DefaultPicoContainer();
        
        PixGateway pixGateway = new PixGateway(
            "12345678901234567890123456789012",
            true
        );
        container.addComponent(PagamentoGateway.class, pixGateway);
        
        container.addComponent(PagamentoService.class);
        
        return container.getComponent(PagamentoService.class);
    }
    
    public static MutablePicoContainer criarContainer() {
        return new DefaultPicoContainer();
    }
    
    public static void registrarGateway(MutablePicoContainer container, PagamentoGateway gateway) {
        container.addComponent(PagamentoGateway.class, gateway);
        container.addComponent(PagamentoService.class);
    }
}
