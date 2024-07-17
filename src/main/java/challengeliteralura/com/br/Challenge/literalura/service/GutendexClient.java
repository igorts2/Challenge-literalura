package challengeliteralura.com.br.Challenge.literalura.service;

import challengeliteralura.com.br.Challenge.literalura.dto.BookResponse;
import challengeliteralura.com.br.Challenge.literalura.entity.Autor;
import challengeliteralura.com.br.Challenge.literalura.entity.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexClient {

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Livro> buscarLivrosPorTitulo(String titulo) throws Exception {
        String url = "https://gutendex.com/books?search=" + titulo;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        BookResponse bookResponse = objectMapper.readValue(responseBody, BookResponse.class);

        // Mapear BookResponse para lista de objetos Livro
        return bookResponse.getResults().stream().map(this::toLivro).toList();
    }

    public List<Livro> listarTodosLivros() throws Exception {
        String url = "https://gutendex.com/books"; // URL para listar todos os livros
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        BookResponse bookResponse = objectMapper.readValue(responseBody, BookResponse.class);

        // Mapear BookResponse para lista de objetos Livro
        return bookResponse.getResults().stream().map(this::toLivro).toList();
    }

    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        // Implemente a lógica para buscar autores
        return autores;
    }

    public List<Autor> listarAutoresVivosNoAno(int ano) {
        List<Autor> autores = new ArrayList<>();
        // Implemente a lógica para buscar autores vivos no ano especificado
        return autores;
    }

    public int contarLivrosPorIdioma(String idioma) throws Exception {
        String url = "https://gutendex.com/books?languages=" + idioma; // URL para buscar livros por idioma
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        BookResponse bookResponse = objectMapper.readValue(responseBody, BookResponse.class);

        // Retornar o número total de livros encontrados no idioma especificado
        return bookResponse.getCount();
    }

    private Livro toLivro(BookResponse.Book book) {
        Livro livro = new Livro();
        livro.setTitulo(book.getTitle());
        livro.setIdioma(String.join(",", book.getLanguages()));
        livro.setNumeroDownloads(book.getDownloadCount());

        if (!book.getAuthors().isEmpty()) {
            Autor autor = new Autor();
            autor.setNome(book.getAuthors().get(0).getName());
            autor.setAnoNascimento(book.getAuthors().get(0).getBirthYear());
            autor.setAnoFalecimento(book.getAuthors().get(0).getDeathYear());
            livro.setAutor(autor);
        }

        return livro;
    }
}
