package avaliacao.acesso.controllers;

import avaliacao.acesso.dto.UserDto;
import avaliacao.acesso.dto.UserResponseDto;
import avaliacao.acesso.entity.Usuario;
import avaliacao.acesso.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<UserResponseDto> buscarTodos() {
        return userService.findAllDtos();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Usuario testGetById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario userPost(@RequestBody UserDto userdto) {
        return this.userService.salvar(userdto);
    }
    @PutMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Usuario userPut(@RequestBody UserDto user) {
        return this.userService.salvar(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void userDelete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
