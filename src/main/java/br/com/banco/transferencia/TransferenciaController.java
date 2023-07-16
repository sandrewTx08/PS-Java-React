package br.com.banco.transferencia;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("transferencia")
public class TransferenciaController {
        @Autowired
        private TransferenciaService transferenciaService;

        @GetMapping("{contaId}")
        public TransferenciaDTO findAllTransferenciaByContaId(
                        Pageable pageable,
                        @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataInicio,
                        @RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime dataFim,
                        @RequestParam(required = false) String nomeOperadorTransacao,
                        @PathVariable Integer contaId) {
                Page<Transferencia> transferencia = transferenciaService
                                .findAllTransferenciaByContaId(pageable, contaId, nomeOperadorTransacao, dataInicio,
                                                dataFim);

                return new TransferenciaDTO(
                                transferencia.getTotalPages(),
                                transferenciaService.saldoTotalByContaId(contaId),
                                transferenciaService.saldoTotalByContaIdAndPeriodo(contaId, dataInicio, dataFim),
                                transferencia.getContent());
        }
}
