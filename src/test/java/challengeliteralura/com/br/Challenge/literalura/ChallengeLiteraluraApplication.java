package challengeliteralura.com.br.Challenge.literalura;

import challengeliteralura.com.br.Challenge.literalura.service.GutendexClient;
import challengeliteralura.com.br.Challenge.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexClient gutendexClient;

	@Autowired
	private LivroService livroService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Menu:");
			System.out.println("1. Buscar livro por título");
			System.out.println("2. Listar todos os livros");
			System.out.println("3. Listar livros por idioma");
			System.out.println("4. Listar autores");
			System.out.println("5. Listar autores vivos em determinado ano");
			System.out.println("6. Sair");
			System.out.print("Escolha uma opção: ");
			int opcao = scanner.nextInt();
			scanner.nextLine();  // Consumir nova linha

			switch (opcao) {
				case 1:
					System.out.print("Digite o título do livro: ");
					String titulo = scanner.nextLine();
					var livros = gutendexClient.buscarLivrosPorTitulo(titulo);
					livros.forEach(livroService::salvarLivro);
					System.out.println("Livros salvos com sucesso!");
					break;
				case 2:
					var todosLivros = livroService.listarTodosLivros();
					todosLivros.forEach(System.out::println);
					break;
				case 3:
					System.out.print("Digite o idioma: ");
					String idioma = scanner.nextLine();
					var livrosPorIdioma = livroService.listarLivrosPorIdioma(idioma);
					livrosPorIdioma.forEach(System.out::println);
					break;
				case 4:
					// Chamar método de listar autores
					// Implemente isso conforme necessário
					break;
				case 5:
					System.out.print("Digite o ano: ");
					int ano = scanner.nextInt();
					// Chamar método de listar autores vivos em determinado ano
					// Implemente isso conforme necessário
					break;
				case 6:
					System.out.println("Saindo...");
					return;
				default:
					System.out.println("Opção inválida!");
			}
		}
	}
}
