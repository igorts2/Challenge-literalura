package challengeliteralura.com.br.Challenge.literalura;

import challengeliteralura.com.br.Challenge.literalura.service.GutendexClient;
import challengeliteralura.com.br.Challenge.literalura.entity.Autor;
import challengeliteralura.com.br.Challenge.literalura.entity.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexClient gutendexClient;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("===== Menu =====");
			System.out.println("1. Buscar livro por título");
			System.out.println("2. Listar todos os livros");
			System.out.println("3. Listar autores");
			System.out.println("4. Listar autores vivos em determinado ano");
			System.out.println("5. Exibir a quantidade de livros em um determinado idioma");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");

			int option = scanner.nextInt();
			scanner.nextLine(); // Consome a nova linha

			switch (option) {
				case 1:
					System.out.print("Digite o título do livro: ");
					String titulo = scanner.nextLine();
					List<Livro> livros = gutendexClient.buscarLivrosPorTitulo(titulo);
					livros.forEach(System.out::println);
					break;
				case 2:
					List<Livro> todosLivros = gutendexClient.listarTodosLivros();
					todosLivros.forEach(System.out::println);
					break;
				case 3:
					List<Autor> autores = gutendexClient.listarAutores();
					autores.forEach(System.out::println);
					break;
				case 4:
					System.out.print("Digite o ano: ");
					int ano = scanner.nextInt();
					List<Autor> autoresVivos = gutendexClient.listarAutoresVivosNoAno(ano);
					autoresVivos.forEach(System.out::println);
					break;
				case 5:
					System.out.print("Digite o idioma (ex: en para inglês): ");
					String idioma = scanner.nextLine();
					int quantidadeLivros = gutendexClient.contarLivrosPorIdioma(idioma);
					System.out.println("Quantidade de livros no idioma '" + idioma + "': " + quantidadeLivros);
					break;
				case 0:
					running = false;
					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		}

		scanner.close();
	}
}
