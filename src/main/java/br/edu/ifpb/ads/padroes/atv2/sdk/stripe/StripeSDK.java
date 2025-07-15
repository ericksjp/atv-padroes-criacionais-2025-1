package br.edu.ifpb.ads.padroes.atv2.sdk.stripe;

import java.math.BigDecimal;

public class StripeSDK {
    
    private String secretKey;
    private String publishableKey;
    
    public StripeSDK(String secretKey, String publishableKey) {
        this.secretKey = secretKey;
        this.publishableKey = publishableKey;
    }
    
    public StripeChargeResult createCharge(StripeChargeRequest request) {
        BigDecimal valorEmReais = new BigDecimal(request.getAmountInCents()).divide(new BigDecimal("100"));
        boolean aprovado = valorEmReais.compareTo(new BigDecimal("800")) <= 0;
        
        StripeChargeResult result = new StripeChargeResult();
        result.setId("ch_" + System.currentTimeMillis());
        result.setPaid(aprovado);
        result.setStatus(aprovado ? "succeeded" : "failed");
        result.setFailureMessage(aprovado ? null : "Valor muito alto");
        
        return result;
    }
    
    public boolean isValidCredentials() {
        return secretKey != null && publishableKey != null;
    }
    
    public static class StripeChargeRequest {
        private long amountInCents;
        private String currency;
        private String cardToken;
        private String description;
        
        public StripeChargeRequest(long amountInCents, String currency, String cardToken, String description) {
            this.amountInCents = amountInCents;
            this.currency = currency;
            this.cardToken = cardToken;
            this.description = description;
        }
        
        public long getAmountInCents() { return amountInCents; }
        public String getCurrency() { return currency; }
        public String getCardToken() { return cardToken; }
        public String getDescription() { return description; }
    }
    
    public static class StripeChargeResult {
        private String id;
        private boolean paid;
        private String status;
        private String failureMessage;
        
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public boolean isPaid() { return paid; }
        public void setPaid(boolean paid) { this.paid = paid; }
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        
        public String getFailureMessage() { return failureMessage; }
        public void setFailureMessage(String failureMessage) { this.failureMessage = failureMessage; }
    }
}
