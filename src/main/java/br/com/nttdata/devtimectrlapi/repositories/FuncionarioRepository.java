package br.com.nttdata.devtimectrlapi.repositories;

import br.com.nttdata.devtimectrlapi.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository  extends JpaRepository<Funcionario, Long> {
    Optional<Funcionario> findByNome(String name);
}
