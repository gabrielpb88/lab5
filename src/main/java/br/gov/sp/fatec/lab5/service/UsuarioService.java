package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.Role;
import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.repository.RoleRepository;
import br.gov.sp.fatec.lab5.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PreAuthorize("isAuthenticated()")
    public Iterable<Usuario> findAll(){
        return repository.findAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Usuario findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Set<Role> roles = usuario.getRoles().stream()
                .map(role -> {
                    Role r = roleRepository.findById(role.getId()).get();
                    r.getUsuarios().add(usuario);
                    roleRepository.save(r);
                    return r;
                })
                .collect(Collectors.toSet());
        usuario.setRoles(roles);
        repository.save(usuario);
    }

    @PreAuthorize("isAuthenticated()")
    public Usuario update(Usuario usuario) {
        return repository.save(usuario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
