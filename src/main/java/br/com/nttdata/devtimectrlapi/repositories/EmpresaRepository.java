package br.com.nttdata.devtimectrlapi.repositories;

import br.com.nttdata.devtimectrlapi.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    List<Empresa> findByNomeContainingIgnoreCase(@Param("name") String name);
}
