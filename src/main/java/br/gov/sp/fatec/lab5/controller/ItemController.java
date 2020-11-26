package br.gov.sp.fatec.lab5.controller;

import br.gov.sp.fatec.lab5.entity.Item;
import br.gov.sp.fatec.lab5.entity.View;
import br.gov.sp.fatec.lab5.service.ItemService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import static br.gov.sp.fatec.lab5.controller.utils.URLs.ITEM;

@CrossOrigin
@RestController
@RequestMapping(ITEM)
public class ItemController {

    @Autowired
    private ItemService service;

    @JsonView(View.UsuarioSimples.class)
    @GetMapping
    public ResponseEntity<Iterable<Item>> buscarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @JsonView(View.UsuarioSimples.class)
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        Item item = service.findById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(item);
    }

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Item item, UriComponentsBuilder uriComponentsBuilder) {
        service.save(item);
        return ResponseEntity.created(uriComponentsBuilder.path(
                ITEM + "/" + item.getId()).build().toUri()).build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizar(@RequestBody Item item) {
        service.update(item);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Item item = service.findById(id);
        if (item == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
