package br.com.nttdata.devtimectrlapi.resourses;

import br.com.nttdata.devtimectrlapi.domain.Cargo;
import br.com.nttdata.devtimectrlapi.repositories.CargoRepository;
import br.com.nttdata.devtimectrlapi.util.RestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/cargos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CargoResources {

    private final CargoRepository cargoRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Cargo>> list() {
        List<Cargo> cargoResponses = cargoRepository.findAll();
        return cargoResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(cargoResponses);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Cargo> getById(@PathVariable("id") Long id) {
        Optional<Cargo> cargo = cargoRepository.findById(id);
        return cargo.isPresent() ? ResponseEntity.ok(cargo.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cargo> add(@Valid @RequestBody Cargo cargo) throws Exception {
        Cargo resultado = cargoRepository.save(cargo);

        URI location = RestUtils.getUri(resultado.getId());
        return ResponseEntity.created(location).body(resultado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Cargo> change(@PathVariable("id") Long id, @RequestBody Cargo cargo) {
        cargo.setId(id);
        Cargo cargoResponse = cargoRepository.save(cargo);
        return cargoResponse != null ?
                ResponseEntity.ok(cargoResponse) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
       cargoRepository.deleteById(id);
       return ResponseEntity.notFound().build();
    }
}
