package avaliacao.acesso.dto;

import avaliacao.acesso.entity.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;
    private String nome;
    private List<Perfil> perfils;
}
