package challengeliteralura.com.br.Challenge.literalura.service;

import challengeliteralura.com.br.Challenge.literalura.entity.Livro;
import challengeliteralura.com.br.Challenge.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public void salvarLivro(Livro livro) {
        livroRepository.save(livro);
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}
