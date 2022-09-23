package br.com.nttdata.devtimectrlapi.resourses;

import br.com.nttdata.devtimectrlapi.domain.Empresa;
import br.com.nttdata.devtimectrlapi.repositories.EmpresaRepository;
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
@RequestMapping("/v1/empresas")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmpresaResources {

    private final EmpresaRepository empresaRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Empresa>> list() {
        List<Empresa> empresaResponses = empresaRepository.findAll();
        return empresaResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(empresaResponses);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Empresa> getById(@PathVariable("id") Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.isPresent() ? ResponseEntity.ok(empresa.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Empresa> add(@Valid @RequestBody Empresa empresa) throws Exception {
        Empresa resultado = empresaRepository.save(empresa);

        URI location = RestUtils.getUri(resultado.getId());
        return ResponseEntity.created(location).body(resultado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Empresa> change(@PathVariable("id") Long id, @RequestBody Empresa empresa) {
        empresa.setId(id);
        Empresa empresaResponse = empresaRepository.save(empresa);
        return empresaResponse != null ?
                ResponseEntity.ok(empresaResponse) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        empresaRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
