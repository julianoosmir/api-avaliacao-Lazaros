package avaliacao.acesso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String nome;

    private String senha;

    private Boolean ativo;
    private List<Long> perfils;
}
