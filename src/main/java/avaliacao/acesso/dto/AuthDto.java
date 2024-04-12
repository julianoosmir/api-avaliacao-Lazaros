package avaliacao.acesso.dto;

import avaliacao.acesso.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private List<Role> role;
    private boolean sign;
    private String token;
}