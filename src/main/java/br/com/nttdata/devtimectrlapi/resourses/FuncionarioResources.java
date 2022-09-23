package br.com.nttdata.devtimectrlapi.resourses;

import br.com.nttdata.devtimectrlapi.domain.Funcionario;
import br.com.nttdata.devtimectrlapi.repositories.FuncionarioRepository;
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
@RequestMapping("/v1/funcionarios")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FuncionarioResources {

    private final FuncionarioRepository funcionarioRepository;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Funcionario>> list() {
        List<Funcionario> occupantResponses = funcionarioRepository.findAll();
        return occupantResponses.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(occupantResponses);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Funcionario> getById(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.isPresent() ? ResponseEntity.ok(funcionario.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Funcionario> add(@Valid @RequestBody Funcionario funcionario) throws Exception {
        Funcionario resultado = funcionarioRepository.save(funcionario);

        URI location = RestUtils.getUri(resultado.getId());
        return ResponseEntity.created(location).body(resultado);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Funcionario> change(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {
        funcionario.setId(id);
        Funcionario occupantResponse = funcionarioRepository.save(funcionario);
        return occupantResponse != null ?
                ResponseEntity.ok(occupantResponse) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable("id") Long id) {
        funcionarioRepository.deleteById(id);
        return ResponseEntity.notFound().build();
    }
}
