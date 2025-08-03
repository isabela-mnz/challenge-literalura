package br.com.literalura.service;

import br.com.literalura.model.Livro;
import br.com.literalura.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository repository;

    public void salvarLivro(Livro livro) {
        repository.save(livro);
    }

    public List<Livro> listarCatalogo() {
        return repository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void removerLivro(Long id) {
        repository.deleteById(id);
    }

    public void atualizarLivro(Livro livro) {
        repository.save(livro);
    }
}
