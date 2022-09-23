package br.com.nttdata.devtimectrlapi.resourses;

import br.com.nttdata.devtimectrlapi.domain.CentroDeCusto;
import br.com.nttdata.devtimectrlapi.repositories.CentroDeCustoRepository;
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
@RequestMapping("/v1/centro-de-custos")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CentroDeCustoResources {

    private final CentroDeCustoRepository centroDeCustoRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CentroDeCusto>> list() {
        List<CentroDeCusto> centroDeCustoResponses = centroDeCustoRepository.findAll();
        return centroDeCustoResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(centroDeCustoResponses);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<CentroDeCusto> getById(@PathVariable("id") Long id) {
        Optional<CentroDeCusto> centroDeCusto = centroDeCustoRepository.findById(id);
        return centroDeCusto.isPresent() ? ResponseEntity.ok(centroDeCusto.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CentroDeCusto> add(@Valid @RequestBody CentroDeCusto centroDeCusto) throws Exception {
        CentroDeCusto resultado = centroDeCustoRepository.save(centroDeCusto);

        URI location = RestUtils.getUri(resultado.getId());
        return ResponseEntity.created(location).body(resultado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CentroDeCusto> change(@PathVariable("id") Long id, @RequestBody CentroDeCusto centroDeCusto) {
        centroDeCusto.setId(id);
        CentroDeCusto centroDeCustoResponse = centroDeCustoRepository.save(centroDeCusto);
        return centroDeCustoResponse != null ?
                ResponseEntity.ok(centroDeCustoResponse) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        centroDeCustoRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
