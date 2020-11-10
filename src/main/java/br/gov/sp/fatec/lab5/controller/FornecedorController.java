package br.gov.sp.fatec.lab5.controller;

import br.gov.sp.fatec.lab5.entity.Fornecedor;
import br.gov.sp.fatec.lab5.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static br.gov.sp.fatec.lab5.controller.utils.URLs.FORNECEDOR;

@CrossOrigin
@RestController
@RequestMapping(FORNECEDOR)
public class FornecedorController {

    @Autowired
    private FornecedorService service;

//    @JsonView(View.UsuarioSimples.class)
    @GetMapping
    public ResponseEntity<Iterable<Fornecedor>> buscarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

//    @JsonView(View.UsuarioCompleto.class)
    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        Fornecedor fornecedor = service.findById(id);
        if (fornecedor == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fornecedor);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Fornecedor fornecedor, UriComponentsBuilder uriComponentsBuilder) {
        service.save(fornecedor);
        return ResponseEntity.created(uriComponentsBuilder.path(
                FORNECEDOR + fornecedor.getId()).build().toUri()).build();
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody Fornecedor fornecedor) {
        service.update(fornecedor);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        Fornecedor fornecedor = service.findById(id);
        if (fornecedor == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
