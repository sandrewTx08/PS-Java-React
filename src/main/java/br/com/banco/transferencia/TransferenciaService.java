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
                LocalDateTime d_inicio = data_inicio != null ? LocalDateTime.parse(data_inicio)
                                : null;
                LocalDateTime d_fim = data_fim != null ? LocalDateTime.parse(data_fim) : null;

                if (data_inicio != null && data_fim != null && nome_operador_transacao == null) {
                        return transferenciaRepositoy
                                        .findAllTransferenciaByContaIdAndDataTransferenciaBetween(pageable,
                                                        conta_id, d_inicio, d_fim);
                } else if (nome_operador_transacao != null && data_fim != null
                                && data_inicio != null) {
                        return transferenciaRepositoy
                                        .findAllTransferenciaByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween(
                                                        pageable, conta_id, nome_operador_transacao, d_inicio,
                                                        d_fim);
                } else if (nome_operador_transacao != null) {
                        return transferenciaRepositoy
                                        .findAllTransferenciaByContaIdAndNomeOperadorTransacao(pageable,
                                                        conta_id,
                                                        nome_operador_transacao);
                } else {
                        return transferenciaRepositoy.findAllTransferenciaByContaId(pageable, conta_id);
                }
        }
}
