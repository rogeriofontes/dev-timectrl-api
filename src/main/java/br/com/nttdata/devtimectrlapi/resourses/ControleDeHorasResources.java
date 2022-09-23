package br.com.nttdata.devtimectrlapi.resourses;

import br.com.nttdata.devtimectrlapi.domain.ControleDeHoras;
import br.com.nttdata.devtimectrlapi.repositories.ControleDeHorasRepository;
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
@RequestMapping("/v1/controle-de-horass")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ControleDeHorasResources {

    private final ControleDeHorasRepository controleDeHorasRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ControleDeHoras>> list() {
        List<ControleDeHoras> controleDeHoraResponses = controleDeHorasRepository.findAll();
        return controleDeHoraResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(controleDeHoraResponses);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<ControleDeHoras> getById(@PathVariable("id") Long id) {
        Optional<ControleDeHoras> controleDeHoras = controleDeHorasRepository.findById(id);
        return controleDeHoras.isPresent() ? ResponseEntity.ok(controleDeHoras.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ControleDeHoras> add(@Valid @RequestBody ControleDeHoras controleDeHoras) throws Exception {
        ControleDeHoras resultado = controleDeHorasRepository.save(controleDeHoras);

        URI location = RestUtils.getUri(resultado.getId());
        return ResponseEntity.created(location).body(resultado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ControleDeHoras> change(@PathVariable("id") Long id, @RequestBody ControleDeHoras controleDeHoras) {
        controleDeHoras.setId(id);
        ControleDeHoras controleDeHoraResponse = controleDeHorasRepository.save(controleDeHoras);
        return controleDeHoraResponse != null ?
                ResponseEntity.ok(controleDeHoraResponse) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        controleDeHorasRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
