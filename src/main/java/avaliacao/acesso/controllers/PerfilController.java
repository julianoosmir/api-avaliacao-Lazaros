package avaliacao.acesso.controllers;

import avaliacao.acesso.entity.Perfil;
import avaliacao.acesso.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<Perfil> getAll() {
        return perfilService.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Perfil savePerfil(@RequestBody Perfil perfil) {
        return perfilService.save(perfil);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Perfil alterarPerfil(@RequestBody Perfil perfil) {
        return perfilService.save(perfil);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Perfil getById(@PathVariable Long id) {
        return perfilService.findById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable Long id) {
        perfilService.delete(id);
    }
}
