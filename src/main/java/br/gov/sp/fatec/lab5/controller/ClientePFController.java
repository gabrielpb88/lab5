package br.gov.sp.fatec.lab5.controller;

import br.gov.sp.fatec.lab5.entity.Cliente;
import br.gov.sp.fatec.lab5.entity.ClientePF;
import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.entity.View;
import br.gov.sp.fatec.lab5.service.ClienteService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/clientePF")
public class ClientePFController {

    @Autowired
    private ClienteService service;

    @JsonView(View.ClienteSimplesPF.class)
    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        return ResponseEntity.ok(service.findAll());
    }

    @JsonView(View.ClienteCompletoPF.class)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        Cliente cliente = service.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @JsonView(View.ClienteSimplesPF.class)
    @GetMapping("/q")
    public ResponseEntity<Iterable<Cliente>> findByNome(@RequestParam String nome){
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody ClientePF cliente, UriComponentsBuilder uriComponentsBuilder) {
        cliente.setId(null);
        service.save(cliente);
        return ResponseEntity.created(uriComponentsBuilder.path(
                "/clientePF/" + cliente.getId()).build().toUri()).build();
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody ClientePF cliente){
        return ResponseEntity.accepted().body(service.update(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        Cliente cliente = service.buscarPorId(id);
        if (cliente == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
