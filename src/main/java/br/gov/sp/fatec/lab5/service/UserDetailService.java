package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.repository.UsuarioRepository;
import br.gov.sp.fatec.lab5.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if( usuario == null ){
            throw new UsernameNotFoundException("Usuário " + email + " não encontrado.");
        }
        return new UserSpringSecurity(usuario.getEmail(), usuario.getSenha(), usuario.getRoles());
    }
}
