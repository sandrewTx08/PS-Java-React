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
                return transferenciaRepositoy
                                .findAllTransferenciaByContaId(
                                                conta_id, nome_operador_transacao,
                                                data_inicio != null && data_fim != null
                                                                ? LocalDateTime.parse(data_inicio)
                                                                : null,
                                                data_inicio != null && data_fim != null ? LocalDateTime.parse(data_fim)
                                                                : null,
                                                pageable);
        }

        public Long saldoTotalByContaId(Integer contaId) {
                return transferenciaRepositoy.saldoTotalByContaId(contaId);
        }
}
