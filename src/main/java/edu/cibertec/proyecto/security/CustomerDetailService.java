package edu.cibertec.proyecto.security;

import edu.cibertec.proyecto.entity.Usuario;
import edu.cibertec.proyecto.repositorios.UsuarioRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class CustomerDetailService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Getter
    private Usuario usuario;
    public User loadUserByUsername(String username){
        usuario = usuarioRepository.findByUsername(username).orElse(null);
        if (usuario == null) {
            log.info("Usuario no encontrado");
            throw new RuntimeException("Usuario no encontrado");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), new ArrayList<>());
    }
}
