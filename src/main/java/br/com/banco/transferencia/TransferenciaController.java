package br.com.banco.transferencia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        @GetMapping("{conta_id}")
        public TransferenciaDTO findAllTransferenciaByContaId(
                        Pageable pageable,
                        @RequestParam(name = "dataInicio", required = false) String dataInicio,
                        @RequestParam(name = "dataFim", required = false) String dataFim,
                        @RequestParam(name = "nomeOperadorTransacao", required = false) String nomeOperadorTransacao,
                        @PathVariable Integer contaId) {
                Page<Transferencia> transferencia = transferenciaService
                                .findAllTransferenciaByContaId(pageable, contaId, nomeOperadorTransacao, dataInicio,
                                                dataFim);

                return new TransferenciaDTO(
                                transferencia.getTotalPages(),
                                transferenciaService.saldoTotalByContaId(contaId),
                                transferenciaService.saldoTotalByContaIdAndPeriodo(contaId, dataInicio, dataInicio),
                                transferencia.getContent());
        }
}
