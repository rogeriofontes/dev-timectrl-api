package br.com.nttdata.devtimectrlapi.repositories;

import br.com.nttdata.devtimectrlapi.domain.ControleDeHoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControleDeHorasRepository extends JpaRepository<ControleDeHoras, Long> {
}
