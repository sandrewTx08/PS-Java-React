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
                boolean temPeriodo = data_inicio != null && data_fim != null;

                return transferenciaRepositoy
                                .findAllTransferenciaByContaId(
                                                conta_id, nome_operador_transacao,
                                                temPeriodo ? LocalDateTime.parse(data_inicio) : null,
                                                temPeriodo ? LocalDateTime.parse(data_fim) : null,
                                                pageable);
        }

        public Long saldoTotalByContaId(Integer contaId) {
                return transferenciaRepositoy.saldoTotalByContaId(contaId);
        }

        public Long saldoTotalByContaIdAndPeriodo(Integer contaId, String dataInicio, String dataFim) {
                boolean temPeriodo = dataInicio != null && dataFim != null;

                return transferenciaRepositoy.saldoTotalByContaIdAndPeriodo(contaId,
                                temPeriodo ? LocalDateTime.parse(dataInicio) : null,
                                temPeriodo ? LocalDateTime.parse(dataFim) : null);
        }
}
