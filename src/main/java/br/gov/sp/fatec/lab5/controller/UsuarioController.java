package br.gov.sp.fatec.lab5.controller;

import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.entity.View;
import br.gov.sp.fatec.lab5.service.UsuarioService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @JsonView(View.UsuarioSimples.class)
    @GetMapping
    public ResponseEntity<Iterable<Usuario>> buscarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @JsonView(View.UsuarioCompleto.class)
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody Usuario usuario, UriComponentsBuilder uriComponentsBuilder) {
        service.save(usuario);
        return ResponseEntity.created(uriComponentsBuilder.path(
                "/usuario/" + usuario.getId()).build().toUri()).build();
    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody Usuario usuario) {
        service.update(usuario);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        Usuario usuario = service.findById(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
