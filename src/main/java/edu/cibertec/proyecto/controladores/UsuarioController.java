package edu.cibertec.proyecto.controladores;

import edu.cibertec.proyecto.dto.UsuarioDto;
import edu.cibertec.proyecto.entity.Usuario;
import edu.cibertec.proyecto.servicios.UsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/usuario")
@Slf4j
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/singup")
    public UsuarioDto singup(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
    @PostMapping("/ejecutarlogin")
    public RedirectView ejectuarlogin(@ModelAttribute (name = "pqteusers") Usuario usuario, HttpServletResponse response, RedirectAttributes attribute) {
        String token = usuarioService.login(usuario.getUsername(), usuario.getPassword());
        if(token == null) {
            attribute.addFlashAttribute("warning", "Datos de usuario incorrectos.");
            return new RedirectView("login");
        }
        RedirectView redirectView = new RedirectView("/listar_Ctrl_Reposicion");
        Cookie cookie = new Cookie("token", token);
        cookie.setPath("/");
        response.addCookie(cookie);
        return redirectView;
    }
    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("autentificacion");
        mav.addObject("pqteusers", new Usuario());
        return mav;
    }
}
