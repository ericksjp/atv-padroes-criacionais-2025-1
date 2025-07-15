package br.edu.ifpb.ads.padroes.atv2.gateway;

import br.edu.ifpb.ads.padroes.atv2.model.PagamentoRequest;
import br.edu.ifpb.ads.padroes.atv2.model.PagamentoResponse;

public interface PagamentoGateway {
    PagamentoResponse processarPagamento(PagamentoRequest request);
    String getNomeGateway();
    boolean isDisponivel();
}
