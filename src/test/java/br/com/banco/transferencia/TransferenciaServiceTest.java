package br.com.banco.transferencia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class TransferenciaServiceTest {
        @Autowired
        private TransferenciaService transferenciaService;

        private Pageable pageable = PageRequest.of(0, 20);

        @Test
        public void findAllTransferenciaByContaIdAndNomeOperadorTransacaoAndDataTransferenciaBetween() {
                List<Transferencia> transferencias = transferenciaService
                                .findAllTransferenciaByContaId(pageable, 1, "Beltrano",
                                                LocalDateTime.parse("2019-06-08T00:00:00"),
                                                LocalDateTime.parse("2020-06-09T00:00:00"))
                                .getContent();

                assertEquals(1, transferencias.size());
                assertEquals("Beltrano", transferencias.get(0).getNomeOperadorTransacao());
        }

        @Test
        public void findAllTransferenciaByContaIdAndNomeOperadorTransacao() {
                List<Transferencia> transferencias = transferenciaService
                                .findAllTransferenciaByContaId(pageable, 1, "Beltrano", null, null)
                                .getContent();

                assertEquals(1, transferencias.size());
                assertEquals(1, transferencias.get(0).getConta().getId());
                assertEquals("Beltrano", transferencias.get(0).getNomeOperadorTransacao());
        }

        @Test
        public void findAllTransferenciaByContaId() {
                List<Transferencia> transferencias = transferenciaService
                                .findAllTransferenciaByContaId(pageable, 1, null, null, null)
                                .getContent();

                assertEquals(3, transferencias.size());
                assertEquals(5, transferencias.get(2).getId());
                assertEquals(1, transferencias.get(0).getConta().getId());
                assertEquals("Beltrano", transferencias.get(2).getNomeOperadorTransacao());
        }

        @Test
        public void findAllTransferenciaByContaIdAndDataTransferenciaBetween2() {
                List<Transferencia> transferencias = transferenciaService
                                .findAllTransferenciaByContaId(pageable, 2, null,
                                                LocalDateTime.parse("2019-06-08T00:00:00"),
                                                LocalDateTime.parse("2021-12-01T00:00:00"))
                                .getContent();

                assertEquals(2, transferencias.size());
                assertEquals("2019-08-07",
                                transferencias.get(0).getDataTransferencia().toLocalDate().toString());
                assertEquals("2021-04-01",
                                transferencias.get(1).getDataTransferencia().toLocalDate().toString());
        }

        @Test
        public void findAllTransferenciaByContaIdAndDataTransferenciaBetween1() {
                List<Transferencia> transferencias = transferenciaService
                                .findAllTransferenciaByContaId(pageable, 2, null,
                                                LocalDateTime.parse("2019-06-08T00:00:00"),
                                                LocalDateTime.parse("2019-12-01T00:00:00"))
                                .getContent();

                assertEquals(1, transferencias.size());
                assertEquals("2019-08-07",
                                transferencias.get(0).getDataTransferencia().toLocalDate().toString());
        }

        @Test
        public void saldoTotalByContaId() {
                BigDecimal transferencias = transferenciaService
                                .saldoTotalByContaId(1);

                assertEquals(BigDecimal.valueOf(30895.46 - 500.50 + 3241.23), transferencias);
        }
}
