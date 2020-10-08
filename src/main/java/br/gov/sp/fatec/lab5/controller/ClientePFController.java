package br.gov.sp.fatec.lab5.controller;

import br.gov.sp.fatec.lab5.entity.Cliente;
import br.gov.sp.fatec.lab5.entity.ClientePF;
import br.gov.sp.fatec.lab5.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/clientesPF")
public class ClientePFController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<Iterable<Cliente>> buscarTodos(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id){
        //TODO: retornar 404 caso o id não exista
        //TODO: tratar erro caso id == null
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/q")
    public ResponseEntity<Cliente> findByNome(@RequestParam String nome){
        return ResponseEntity.ok(service.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody ClientePF cliente) {
        cliente.setId(null);
        service.save(cliente);
        //TODO: alterar para retornar a URL com id do recurso criado
        return ResponseEntity.ok(cliente);
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody ClientePF cliente){
        return ResponseEntity.accepted().body(service.update(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){

        // TODO: retornar 404 se o item a ser deletado não existir.
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
