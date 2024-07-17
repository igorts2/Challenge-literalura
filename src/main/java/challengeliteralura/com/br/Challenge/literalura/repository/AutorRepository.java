package challengeliteralura.com.br.Challenge.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import challengeliteralura.com.br.Challenge.literalura.entity.Autor;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByAnoNascimentoBeforeAndAnoFalecimentoAfter(Integer anoInicio, Integer anoFim);
}
