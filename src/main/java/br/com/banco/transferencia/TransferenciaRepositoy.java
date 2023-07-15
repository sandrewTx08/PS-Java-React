package br.com.banco.transferencia;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepositoy extends PagingAndSortingRepository<Transferencia, Integer> {
        @Query("SELECT t FROM Transferencia t WHERE t.conta.id = ?1" +
                        " AND (?2 IS NULL OR t.nomeOperadorTransacao = ?2)" +
                        " AND ((t.dataTransferencia BETWEEN ?3 AND ?4) OR ((CAST(?3 AS timestamp) IS NULL) OR (CAST(?4 AS timestamp) IS NULL)))")
        public Page<Transferencia> findAllTransferenciaByContaId(
                        Integer contaId, String nomeOperadorTransacao,
                        LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);

        @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta.id = ?1")
        public Long saldoTotalByContaId(Integer contaId);

        @Query("SELECT SUM(t.valor) FROM Transferencia t WHERE t.conta.id = ?1 AND ((t.dataTransferencia BETWEEN ?2 AND ?3) OR ((CAST(?2 AS timestamp) IS NULL) OR (CAST(?3 AS timestamp) IS NULL)))")
        public Long saldoTotalByContaIdAndPeriodo(Integer contaId, LocalDateTime dataInicio, LocalDateTime dataFim);
}
