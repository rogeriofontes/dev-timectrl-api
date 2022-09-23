package br.com.nttdata.devtimectrlapi.repositories;

import br.com.nttdata.devtimectrlapi.domain.CentroDeCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long> {
}
