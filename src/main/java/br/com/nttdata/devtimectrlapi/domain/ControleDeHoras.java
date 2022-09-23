package br.com.nttdata.devtimectrlapi.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_controle_de_horas")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ControleDeHoras implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;
    @Column(name = "entrada_manha")
    private LocalDateTime entradaManha;
    @Column(name = "saida_manha")
    private LocalDateTime saidaManha;
    @Column(name = "entrada_tarde")
    private LocalDateTime entradaTarde;
    @Column(name = "saida_tarde")
    private LocalDateTime saidaTarde;
    @Column(name = "entrada_noite")
    private LocalDateTime entradaNoite;
    @Column(name = "saida_noite")
    private LocalDateTime saidaNoite;
    @Column(name = "entrada_madrugada")
    private LocalDateTime entradaMadrugada;
    @Column(name = "saida_madrugada")
    private LocalDateTime saidaMadrugada;
    private int quantidade;
    @Column(name = "tipo_de_dia")
    @Enumerated(EnumType.STRING)
    private TipoDeDia tipoDeDia;

}