package br.com.nttdata.devtimectrlapi;

import br.com.nttdata.devtimectrlapi.domain.*;
import br.com.nttdata.devtimectrlapi.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@Slf4j
public class DevTimectrlApiApplication implements CommandLineRunner {

	@Autowired
	private EmpresaRepository empresaRepository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private CentroDeCustoRepository centroDeCustoRepository;

	@Autowired
	private ControleDeHorasRepository controleDeHorasRepository;

	public static void main(String[] args) {
		SpringApplication.run(DevTimectrlApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Empresa empresa = Empresa.builder()
				.nome("NTT DATA BRASIL CONSULTORIA DE NEGOCIOS E T.I. LTDA")
				.cnpj("2345")
				.endereco("Endereço: Avenida CESARIO ALVIM , 5606 - MARILEUSA (LOT.CONVENCIONAL) - UBERLÂNDIA - MG")
				.tipoEmpresa(TipoEmpresa.CENTER)
				.build();

		empresaRepository.save(empresa);

		Optional<Empresa> empresa1 = empresaRepository.findById(1l);

		CentroDeCusto centroDeCusto  = CentroDeCusto.builder()
				.nome("3072-CABRUDI301")
				.empresa(empresa1.isPresent() ? empresa1.get() : null)
				.build();

		centroDeCustoRepository.save(centroDeCusto);

		Optional<CentroDeCusto> centroDeCusto1 = centroDeCustoRepository.findById(1l);

		Cargo cargo = Cargo.builder()
				.nome("CSS ESPECIALISTA DE SISTEMAS")
				.empresa(empresa1.isPresent() ? empresa1.get() : null)
				.build();

		cargoRepository.save(cargo);

		Optional<Cargo>  cargo1 = cargoRepository.findById(1l);

		Funcionario funcionarioGestor = Funcionario.builder()
				.nome("Marcelo Vidigal Dias")
				.email("marcelo.vidigal.dias@nttdata.com")
				.cpf("23432")
				.matricula("2233")
				.dataAdmissao(LocalDateTime.now())
				.ctps(123)
				.pisPasep(233)
				.horario(Horario.SEG_SEX)
				.funcionario(null)
				.centroDeCusto(centroDeCusto1.isPresent() ? centroDeCusto1.get() : null)
				.cargo(cargo1.isPresent() ? cargo1.get() : null)
				.tipoFuncionario(TipoFuncionario.DESENVOVEDOR)
				.build();

		funcionarioRepository.save(funcionarioGestor);

		Optional<Funcionario> gestorMarceloVidigalDias = funcionarioRepository.findByNome("Marcelo Vidigal Dias");

		Funcionario funcionario = Funcionario.builder()
				.nome("JAIRO NASCIMENTO DE SOUSA FILHO")
				.email("jairo@nttdata.com")
				.cpf("23432")
				.matricula("2233")
				.dataAdmissao(LocalDateTime.now())
				.ctps(123)
				.pisPasep(233)
				.horario(Horario.SEG_SEX)
				.funcionario(gestorMarceloVidigalDias.isPresent() ? gestorMarceloVidigalDias.get() : null)
				.centroDeCusto(centroDeCusto1.isPresent() ? centroDeCusto1.get() : null)
				.cargo(cargo1.isPresent() ? cargo1.get() : null)
				.tipoFuncionario(TipoFuncionario.DESENVOVEDOR)
				.build();

		funcionarioRepository.save(funcionario);

		Optional<Funcionario> funcionario1 = funcionarioRepository.findById(1l);

		ControleDeHoras controleDeHoras = ControleDeHoras.builder()
				.funcionario(funcionario1.isPresent() ? funcionario1.get() : null)
				.entradaManha(LocalDateTime.now())
				.saidaManha(LocalDateTime.now())
				.entradaTarde(LocalDateTime.now())
				.saidaTarde(LocalDateTime.now())
				.entradaNoite(LocalDateTime.now())
				.saidaNoite(LocalDateTime.now())
				.entradaMadrugada(LocalDateTime.now())
				.saidaMadrugada(LocalDateTime.now())
				.tipoDeDia(TipoDeDia.DIA_NORMAL_DE_TRABALHO)
				.quantidade(11)
				.build();

		controleDeHorasRepository.save(controleDeHoras);

		List<ControleDeHoras> all = controleDeHorasRepository.findAll();
		log.info("Da" + all);
	}
}
