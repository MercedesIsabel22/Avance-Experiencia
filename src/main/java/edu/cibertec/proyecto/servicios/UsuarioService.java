package edu.cibertec.proyecto.servicios;

import edu.cibertec.proyecto.dto.UsuarioDto;
import edu.cibertec.proyecto.entity.Usuario;

public interface UsuarioService {
    public UsuarioDto save(Usuario usuario);
    public UsuarioDto findByUsername(String username);
    public String login(String username, String password);
}
