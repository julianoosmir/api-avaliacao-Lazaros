package avaliacao.acesso.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
public class Role implements GrantedAuthority {

    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }
}

