package br.com.banco.transferencia;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class TransferenciaDTO {
    public Integer totalDePaginas;
    public BigDecimal saldoTotal;
    public BigDecimal saldoTotalDoPeriodo;
    public List<Transferencia> transferencias;
}
