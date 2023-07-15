package br.com.banco.transferencia;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {
        @Autowired
        private TransferenciaRepositoy transferenciaRepositoy;

        public Page<Transferencia> findAllTransferenciaByContaId(Pageable pageable, Integer contaId,
                        String nomeOperadorTransacao, String dataInicio, String dataFim) {
                boolean temPeriodo = dataInicio != null && dataFim != null;

                return transferenciaRepositoy
                                .findAllTransferenciaByContaId(
                                                contaId, nomeOperadorTransacao != "" ? nomeOperadorTransacao : null,
                                                temPeriodo ? LocalDateTime.parse(dataInicio) : null,
                                                temPeriodo ? LocalDateTime.parse(dataFim) : null,
                                                pageable);
        }

        public BigDecimal saldoTotalByContaId(Integer contaId) {
                return transferenciaRepositoy.saldoTotalByContaId(contaId);
        }

        public BigDecimal saldoTotalByContaIdAndPeriodo(Integer contaId, String dataInicio, String dataFim) {
                boolean temPeriodo = dataInicio != null && dataFim != null;

                return transferenciaRepositoy.saldoTotalByContaIdAndPeriodo(contaId,
                                temPeriodo ? LocalDateTime.parse(dataInicio) : null,
                                temPeriodo ? LocalDateTime.parse(dataFim) : null);
        }
}
