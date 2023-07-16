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
                        String nomeOperadorTransacao, LocalDateTime dataInicio, LocalDateTime dataFim) {
                return transferenciaRepositoy
                                .findAllTransferenciaByContaId(contaId,
                                                nomeOperadorTransacao != "" ? nomeOperadorTransacao : null, dataInicio,
                                                dataFim, pageable);
        }

        public BigDecimal saldoTotalByContaId(Integer contaId) {
                return transferenciaRepositoy.saldoTotalByContaId(contaId);
        }

        public BigDecimal saldoTotalByContaIdAndPeriodo(Integer contaId, LocalDateTime dataInicio,
                        LocalDateTime dataFim) {
                return transferenciaRepositoy.saldoTotalByContaIdAndPeriodo(
                                contaId,
                                dataInicio,
                                dataFim);
        }
}
