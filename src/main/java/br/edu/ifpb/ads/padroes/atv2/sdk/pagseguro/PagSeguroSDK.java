package br.edu.ifpb.ads.padroes.atv2.sdk.pagseguro;

import java.math.BigDecimal;

public class PagSeguroSDK {
    
    private String email;
    private String token;
    
    public PagSeguroSDK(String email, String token, boolean sandbox) {
        this.email = email;
        this.token = token;
    }
    
    public PagSeguroTransactionResult createTransaction(PagSeguroTransactionRequest request) {
        boolean aprovado = request.getAmount().compareTo(new BigDecimal("1000")) <= 0;
        
        PagSeguroTransactionResult result = new PagSeguroTransactionResult();
        result.setCode("PS_" + System.currentTimeMillis());
        result.setStatus(aprovado ? 3 : 7);
        result.setStatusMessage(aprovado ? "Aprovado" : "Recusado");
        
        return result;
    }
    
    public boolean isAuthenticated() {
        return email != null && token != null;
    }
    
    public static class PagSeguroTransactionRequest {
        private String reference;
        private BigDecimal amount;
        private String senderEmail;
        private String senderName;
        
        public PagSeguroTransactionRequest(String reference, BigDecimal amount, String senderEmail, String senderName) {
            this.reference = reference;
            this.amount = amount;
            this.senderEmail = senderEmail;
            this.senderName = senderName;
        }
        
        public String getReference() { return reference; }
        public BigDecimal getAmount() { return amount; }
        public String getSenderEmail() { return senderEmail; }
        public String getSenderName() { return senderName; }
    }
    
    public static class PagSeguroTransactionResult {
        private String code;
        private int status;
        private String statusMessage;
        
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        
        public String getStatusMessage() { return statusMessage; }
        public void setStatusMessage(String statusMessage) { this.statusMessage = statusMessage; }
    }
}
