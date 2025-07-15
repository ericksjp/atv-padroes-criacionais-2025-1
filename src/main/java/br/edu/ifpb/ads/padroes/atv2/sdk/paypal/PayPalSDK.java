package br.edu.ifpb.ads.padroes.atv2.sdk.paypal;

import java.math.BigDecimal;

public class PayPalSDK {
    
    private String apiKey;
    private String secretKey;
    
    public PayPalSDK(String apiKey, String secretKey, boolean sandboxMode) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }
    
    public PayPalPaymentResult createPayment(PayPalPaymentRequest request) {
        boolean aprovado = request.getAmount().compareTo(new BigDecimal("500")) <= 0;
        
        PayPalPaymentResult result = new PayPalPaymentResult();
        result.setPaymentId("PP_" + System.currentTimeMillis());
        result.setState(aprovado ? "approved" : "failed");
        result.setMessage(aprovado ? "Aprovado" : "Recusado");
        
        return result;
    }
    
    public boolean isConnected() {
        return apiKey != null && secretKey != null;
    }
    
    public static class PayPalPaymentRequest {
        private BigDecimal amount;
        private String currency;
        private String payerEmail;
        private String description;
        
        public PayPalPaymentRequest(BigDecimal amount, String currency, String payerEmail, String description) {
            this.amount = amount;
            this.currency = currency;
            this.payerEmail = payerEmail;
            this.description = description;
        }
        
        public BigDecimal getAmount() { return amount; }
        public String getCurrency() { return currency; }
        public String getPayerEmail() { return payerEmail; }
        public String getDescription() { return description; }
    }
    
    public static class PayPalPaymentResult {
        private String paymentId;
        private String state;
        private String message;
        
        public String getPaymentId() { return paymentId; }
        public void setPaymentId(String paymentId) { this.paymentId = paymentId; }
        
        public String getState() { return state; }
        public void setState(String state) { this.state = state; }
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
}
