package br.com.banco.transferencia;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Transferencia> findAllTransferenciaByContaId(
            Pageable pageable,
            @RequestParam(name = "data_inicio", required = false) String data_inicio,
            @RequestParam(name = "data_fim", required = false) String data_fim,
            @RequestParam(name = "nome_operador_transacao", required = false) String nome_operador_transacao,
            @PathVariable Integer conta_id) {
        return transferenciaService
                .findAllTransferenciaByContaId(pageable, conta_id, nome_operador_transacao, data_inicio, data_fim)
                .getContent();
    }
}
