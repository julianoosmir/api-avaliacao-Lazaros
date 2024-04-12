package avaliacao.acesso.service;

import avaliacao.acesso.entity.Perfil;
import avaliacao.acesso.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepository repository;

    public Perfil save(Perfil perfil) {
        return repository.save(perfil);
    }

    public Perfil savePerfil(String perfilName) {
        Perfil perfil = new Perfil();
        perfil.setName(perfilName);
        return repository.save(perfil);
    }

    public Perfil findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Perfil> findALLById(List<Long> id) {
        return repository.findAllById(id);
    }

    public List<Perfil> findAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
