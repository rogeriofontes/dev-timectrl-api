package br.com.nttdata.devtimectrlapi.repositories;

import br.com.nttdata.devtimectrlapi.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {
}
