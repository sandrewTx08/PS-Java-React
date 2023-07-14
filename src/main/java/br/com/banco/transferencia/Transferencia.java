package br.com.banco.transferencia;

import br.com.banco.conta.Conta;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Getter
@Setter
@Entity
@Table(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "data_transferencia", nullable = false)
    private LocalDateTime dataTransferencia;

    @Column(name = "valor", nullable = false, precision = 20, scale = 2)
    private BigDecimal valor;

    @Column(name = "tipo", nullable = false, length = 15)
    private String tipo;

    @Column(name = "nome_operador_transacao", length = 50)
    private String nomeOperadorTransacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_id", nullable = false)
    @JsonBackReference
    private Conta conta;

}