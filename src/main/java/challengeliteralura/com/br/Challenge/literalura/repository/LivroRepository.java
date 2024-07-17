package challengeliteralura.com.br.Challenge.literalura.repository;

import challengeliteralura.com.br.Challenge.literalura.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(String idioma);
}