package br.com.banco.transferencia;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class TransferenciaDTO {
    public Integer totalDePaginas;
    public Long saldoTotal;
    public Long saldoTotalDoPeriodo;
    public List<Transferencia> transferencias;
}
