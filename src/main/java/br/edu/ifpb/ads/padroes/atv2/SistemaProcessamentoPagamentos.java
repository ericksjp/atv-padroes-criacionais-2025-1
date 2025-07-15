package br.edu.ifpb.ads.padroes.atv2;

import br.edu.ifpb.ads.padroes.atv2.config.PagamentoConfig;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoRequest;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoResponse;
import br.edu.ifpb.ads.padroes.atv2.service.PagamentoService;

import java.math.BigDecimal;
import java.util.UUID;

public class SistemaProcessamentoPagamentos {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE PROCESSAMENTO DE PAGAMENTOS ===");
        System.out.println("Demonstração de múltiplos gateways com injeção de dependência\n");
        
        PagamentoRequest pagamento1 = criarPagamentoTeste("João Silva", "joao@email.com", new BigDecimal("299.99"));
        PagamentoRequest pagamento2 = criarPagamentoTeste("Maria Santos", "maria@email.com", new BigDecimal("1500.00"));
        PagamentoRequest pagamento3 = criarPagamentoTeste("Pedro Costa", "pedro@teste.com", new BigDecimal("899.90"));
        PagamentoRequest pagamento4 = criarPagamentoTeste("Ana Lima", "ana@email.com", new BigDecimal("3000.00"));
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("1. TESTANDO GATEWAY PAYPAL");
        System.out.println("=".repeat(60));
        
        PagamentoService pagamentoServicePayPal = PagamentoConfig.criarPagamentoServicePayPal();
        System.out.println("✓ PagamentoService criado via injeção de dependência");
        System.out.println("✓ " + pagamentoServicePayPal.getGatewayInfo());
        
        processarPagamento(pagamentoServicePayPal, pagamento1, "Pagamento 1 - PayPal");
        
        // teste com Stripe
        System.out.println("\n" + "=".repeat(60));
        System.out.println("2. TESTANDO GATEWAY STRIPE");
        System.out.println("=".repeat(60));
        
        PagamentoService pagamentoServiceStripe = PagamentoConfig.criarPagamentoServiceStripe();
        System.out.println("✓ PagamentoService criado com gateway diferente");
        System.out.println("✓ " + pagamentoServiceStripe.getGatewayInfo());
        
        processarPagamento(pagamentoServiceStripe, pagamento2, "Pagamento 2 - Stripe");
        
        // teste com PagSeguro
        System.out.println("\n" + "=".repeat(60));
        System.out.println("3. TESTANDO GATEWAY PAGSEGURO");
        System.out.println("=".repeat(60));
        
        PagamentoService pagamentoServicePagSeguro = PagamentoConfig.criarPagamentoServicePagSeguro();
        System.out.println("✓ PagamentoService criado com terceiro gateway");
        System.out.println("✓ " + pagamentoServicePagSeguro.getGatewayInfo());
        
        processarPagamento(pagamentoServicePagSeguro, pagamento3, "Pagamento 3 - PagSeguro");
        
        // teste com PIX (demonstra extensibilidade)
        System.out.println("\n" + "=".repeat(60));
        System.out.println("4. TESTANDO NOVO GATEWAY PIX (EXTENSIBILIDADE)");
        System.out.println("=".repeat(60));
        
        PagamentoService pagamentoServicePix = PagamentoConfig.criarPagamentoServicePix();
        System.out.println("✓ Novo gateway PIX adicionado sem modificar PagamentoService");
        System.out.println("✓ " + pagamentoServicePix.getGatewayInfo());
        
        processarPagamento(pagamentoServicePix, pagamento4, "Pagamento 4 - PIX");
        
        // demonstrar diferentes comportamentos
        System.out.println("\n" + "=".repeat(60));
        System.out.println("5. COMPARANDO RESULTADOS DOS GATEWAYS");
        System.out.println("=".repeat(60));
        
        compararGateways();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DEMONSTRAÇÃO CONCLUÍDA");
        System.out.println("=".repeat(60));
        System.out.println("✓ Sistema suporta múltiplos gateways de pagamento");
        System.out.println("✓ Injeção de dependência implementada com PicoContainer");
        System.out.println("✓ Facilmente extensível para novos provedores");
        System.out.println("✓ Princípios SOLID respeitados (especialmente DIP)");
    }

    private static void processarPagamento(PagamentoService service, PagamentoRequest request, String descricao) {
        System.out.println("\n" + "-".repeat(40));
        System.out.println(descricao);
        System.out.println("-".repeat(40));
        
        try {
            PagamentoResponse response = service.pagar(request);
            
            System.out.println("\n📋 RESULTADO:");
            System.out.println("   Status: " + response.getStatus());
            System.out.println("   Gateway: " + response.getNomeGateway());
            System.out.println("   ID Gateway: " + response.getIdGateway());
            System.out.println("   Mensagem: " + response.getMensagem());
            System.out.println("   Data: " + response.getDataProcessamento());
            
            if (response.isAprovado()) {
                System.out.println("PAGAMENTO APROVADO");
            } else {
                System.out.println("PAGAMENTO NÃO APROVADO");
            }
            
        } catch (Exception e) {
            System.err.println("ERRO: " + e.getMessage());
        }
    }
    
    private static PagamentoRequest criarPagamentoTeste(String titular, String email, BigDecimal valor) {
        String transacaoId = "TXN_" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        return new PagamentoRequest(
            transacaoId,
            valor,
            "BRL",
            "4111111111111111",
            titular,
            "12/28",
            "123",
            email,
            "Compra teste - " + titular
        );
    }
    
    private static void compararGateways() {
        PagamentoRequest testePequeno = criarPagamentoTeste("Teste Pequeno", "teste@email.com", new BigDecimal("50.00"));
        PagamentoRequest testeGrande = criarPagamentoTeste("Teste Grande", "teste@email.com", new BigDecimal("2000.00"));
        
        System.out.println("Comparando comportamento com valores diferentes:");
        
        System.out.println("\n🔸 PayPal (rejeita > R$ 1000):");
        PagamentoService paypal = PagamentoConfig.criarPagamentoServicePayPal();
        PagamentoResponse respPayPal = paypal.pagar(testeGrande);
        System.out.println("   " + testeGrande.getValor() + " → " + respPayPal.getStatus());
        
        System.out.println("\n🔸 Stripe (rejeita cartões terminados em 0000):");
        PagamentoService stripe = PagamentoConfig.criarPagamentoServiceStripe();
        PagamentoResponse respStripe = stripe.pagar(testePequeno);
        System.out.println("   Cartão 4111111111111111 → " + respStripe.getStatus());
        
        System.out.println("\n🔸 PIX (limite R$ 5000):");
        PagamentoService pix = PagamentoConfig.criarPagamentoServicePix();
        PagamentoResponse respPix = pix.pagar(testeGrande);
        System.out.println("   " + testeGrande.getValor() + " → " + respPix.getStatus());
    }
}
