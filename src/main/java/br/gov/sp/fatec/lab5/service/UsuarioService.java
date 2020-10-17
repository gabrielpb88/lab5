package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.Role;
import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.repository.RoleRepository;
import br.gov.sp.fatec.lab5.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

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

    @PreAuthorize("isAuthenticated()")
    public Usuario findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Set<Role> roles = usuario.getRoles().stream()
                .map(role -> roleRepository.findById(role.getId()).get())
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

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = repository.findByNome(nome);
        if( usuario == null) {
            throw new UsernameNotFoundException("Usuário " + nome + " não encontrado.");
        }
        return User.builder().username(usuario.getNome())
                .password(usuario.getSenha())
                .authorities(usuario.getRoles().stream()
                        .map(Role::getRole).collect(Collectors.toList())
                        .toArray(new String[usuario.getRoles().size()]))
                .build();
    }

}
