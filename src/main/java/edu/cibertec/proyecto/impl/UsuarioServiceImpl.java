package edu.cibertec.proyecto.impl;

import edu.cibertec.proyecto.dto.UsuarioDto;
import edu.cibertec.proyecto.entity.Usuario;
import edu.cibertec.proyecto.repositorios.UsuarioRepository;
import edu.cibertec.proyecto.security.jwt.JwtUtil;
import edu.cibertec.proyecto.servicios.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public UsuarioDto save(Usuario usuario) {
        Usuario usuarioSaved = usuarioRepository.save(usuario);
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setUsername(usuarioSaved.getUsername());
        usuarioDto.setEmail(usuarioSaved.getEmail());
        log.info("Creando usuario");
        return usuarioDto;
    }

    @Override
    public UsuarioDto findByUsername(String username) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        try
        {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password
                    ));
            if (authentication.isAuthenticated()) {
                return jwtUtil.generateToken(username, "ADMIN");
            }
        }
        catch (Exception e) {
            log.error("Error en el login", e);
            System.out.println(e.getCause());
        }

        return null;
    }
}
