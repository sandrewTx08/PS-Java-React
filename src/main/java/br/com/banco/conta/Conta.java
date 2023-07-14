package br.com.banco.conta;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.com.banco.transferencia.Transferencia;

@Getter
@Setter
@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta", nullable = false)
    private Integer id;

    @Column(name = "nome_responsavel", nullable = false, length = 50)
    private String nomeResponsavel;

    @OneToMany(mappedBy = "conta")
    @JsonBackReference
    private Set<Transferencia> transferencias = new LinkedHashSet<>();

}