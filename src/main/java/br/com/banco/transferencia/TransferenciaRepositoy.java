package br.com.banco.transferencia;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepositoy extends PagingAndSortingRepository<Transferencia, Integer> {
        public Page<Transferencia> findAllTransferenciaByContaId(
                        Pageable pageable,
                        Integer contaId);

        public Page<Transferencia> findAllTransferenciaByContaIdAndNomeOperadorTransacao(
                        Pageable pageable, Integer contaId, String nomeOperadorTransacao);

        public Page<Transferencia> findAllTransferenciaByContaIdAndDataTransferenciaBetween(
                        Pageable pageable, Integer contaId,
                        LocalDateTime data_inicio, LocalDateTime data_fim);

        public Page<Transferencia> findAllTransferenciaByNomeOperadorTransacaoAndDataTransferenciaBetween(
                        Pageable pageable, String nomeOperadorTransacao,
                        LocalDateTime data_inicio, LocalDateTime data_fim);
}
