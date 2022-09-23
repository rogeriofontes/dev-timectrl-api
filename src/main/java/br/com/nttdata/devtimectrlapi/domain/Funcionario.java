package br.com.nttdata.devtimectrlapi.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_funcionario")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Funcionario implements Serializable  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String matricula;
    @Column(name = "data_admissao")
    private LocalDateTime dataAdmissao;
    private int ctps;
    @Column(name = "pis_pasep")
    private int pisPasep;

    @Enumerated(EnumType.STRING)
    private Horario horario;

    @ManyToOne(optional=true)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "id_centro_de_custo")
    private CentroDeCusto centroDeCusto;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    @Column(name = "tipo_funcionario")
    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipoFuncionario;
}
