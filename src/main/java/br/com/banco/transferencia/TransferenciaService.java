package br.com.banco.transferencia;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {
        @Autowired
        private TransferenciaRepositoy transferenciaRepositoy;

        public Page<Transferencia> findAllTransferenciaByContaId(Pageable pageable, Integer conta_id,
                        String nome_operador_transacao, String data_inicio, String data_fim) {
                Page<Transferencia> transferencia;

                LocalDateTime d_inicio = data_inicio != null ? LocalDateTime.parse(data_inicio)
                                : LocalDateTime.of(1990, 1, 1, 0, 0, 0, 0);
                LocalDateTime d_fim = data_fim != null ? LocalDateTime.parse(data_fim) : LocalDateTime.now();

                if (conta_id != null) {
                        if (data_inicio != null && data_fim != null && nome_operador_transacao == null) {
                                transferencia = transferenciaRepositoy
                                                .findAllTransferenciaByContaIdAndDataTransferenciaBetween(pageable,
                                                                conta_id, d_inicio, d_fim);
                        } else if (nome_operador_transacao != null) {
                                transferencia = transferenciaRepositoy
                                                .findAllTransferenciaByContaIdAndNomeOperadorTransacao(pageable,
                                                                conta_id,
                                                                nome_operador_transacao);
                        } else {
                                transferencia = transferenciaRepositoy.findAll(pageable);
                        }
                } else {
                        transferencia = transferenciaRepositoy
                                        .findAllTransferenciaByNomeOperadorTransacaoAndDataTransferenciaBetween(
                                                        pageable, nome_operador_transacao, d_inicio,
                                                        d_fim);
                }

                return transferencia;
        }

        public Page<Transferencia> findAllTransferencia(Pageable pageable) {
                return transferenciaRepositoy.findAll(pageable);
        }
}
