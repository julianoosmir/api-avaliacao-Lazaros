package avaliacao.acesso.service;

import avaliacao.acesso.config.jwt.JwtRequest;
import avaliacao.acesso.config.jwt.JwtTokenUtil;
import avaliacao.acesso.dto.AuthDto;
import avaliacao.acesso.dto.UserDto;
import avaliacao.acesso.dto.UserResponseDto;
import avaliacao.acesso.entity.Usuario;
import avaliacao.acesso.entity.Perfil;
import avaliacao.acesso.model.Role;
import avaliacao.acesso.model.UserSecurity;
import avaliacao.acesso.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    public AuthenticationManager authenticationManager;

    public UserDetails loadUserByUsername(String username) {
        final UserDetails userDetails = loadUserSegurityByUsername(username);
        return userDetails;
    }
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }
    public void delete(Long id) {
        this.usuarioRepository.deleteById(id);
    }
    public Usuario salvar(UserDto userdto) {
        userdto.setSenha(this.enconderPassword(userdto.getSenha()));
        Usuario user = this.mapper.map(userdto, Usuario.class);
        List<Perfil> perfils = perfilService.findALLById(userdto.getPerfils());
        user.setPerfils(perfils);
        return usuarioRepository.save(user);
    }

    private UserSecurity loadUserSegurityByUsername(String username) {
        Usuario usuario = usuarioRepository.findByNome(username);

        List<Role> roles = getRolesPefil(usuario.getPerfils());

        return UserSecurity.builder()
                .roles(roles)
                .ativo(usuario.getAtivo())
                .username(usuario.getNome())
                .password(usuario.getSenha())
                .build();

    }


    public List<UserResponseDto> findAllDtos() {
        List<UserResponseDto> userdtos = new ArrayList<>();
        this.buscarTodosUsuarios().forEach(user -> {
            UserResponseDto dto = new UserResponseDto(user.getId(), user.getNome(),user.getPerfils());
            userdtos.add(dto);
        });
        return userdtos;
    }

    private List<Role> getRolesPefil(List<Perfil> perfils) {
        return perfils.stream().map(p -> Role.builder().name(p.getName()).build())
                .collect(Collectors.toList());
    }

    public List<Usuario> buscarTodosUsuarios() {
        return this.usuarioRepository.findAll();
    }

    public ResponseEntity<?> signin(JwtRequest authenticationRequest) {

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserSecurity userSegurity = loadUserSegurityByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userSegurity);

           // final AuthDto authDto = new AuthDto((userSegurity.getRoles()), true, token);

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIAIS INVALIDAS", e);
        } catch (InternalAuthenticationServiceException e) {
            throw new Exception("USUARIO NÃO CADASTRADO", e);
        }
    }
    private String enconderPassword(String senha) {
        return new BCryptPasswordEncoder().encode(senha);
    }


}
