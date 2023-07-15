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
    private Integer totalDePaginas;
    private BigDecimal saldoTotal;
    private BigDecimal saldoTotalDoPeriodo;
    private List<Transferencia> transferencias;
}
