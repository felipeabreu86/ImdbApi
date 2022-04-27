package br.com.alura.imdb.utils;

import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import br.com.alura.imdb.models.Movie;

/**
 * Classe responsável por gerar um arquivo HTML para exibição dos filmes.
 * 
 * @author Felipe Abreu
 */
public class HTMLGenerator {

	// Construtores

	/**
	 * Construtor que recebe uma instância de PrintWriter.
	 * 
	 * @param writer PrintWriter
	 */
	public HTMLGenerator(PrintWriter writer) {
		Objects.requireNonNull(writer);
		this.writer = writer;
	}

	// Atributos de instância

	private PrintWriter writer;

	// Métodos

	/**
	 * Método que gera um arquivo HTML baseado na lista de filmes.
	 * 
	 * @param filmes Lista de filmes.
	 */
	public void generate(List<Movie> filmes) {
		Objects.requireNonNull(filmes);

		ordenarFilmes(filmes);

		writer.println("<!DOCTYPE html>");
		writer.println("<html>");
		writer.println("<head>");
		writer.println("<meta charset='UTF-8'/>");
		writer.println("<title>Document</title>");
		writer.println("</head>");
		writer.println("<body>");

		String divTemplate = """
				<div class=\"card text-white bg-dark mb-3\" style=\"max-width: 18rem;\">
					<h4 class=\"card-header\">%s</h4>
					<div class=\"card-body\">
						<img class=\"card-img\" src=\"%s\" alt=\"%s\">
						<p class=\"card-text mt-2\">Nota: %s - Ano: %s</p>
					</div>
				</div>
				""";

		for (int iterator = 0; iterator < filmes.size(); iterator++) {
			Movie movie = filmes.get(iterator);
			writer.println(String.format(divTemplate, movie.getTitle(), movie.getImage(), movie.getTitle(),
					movie.getImDbRating(), movie.getYear()));
		}

		writer.println("</body>");
		writer.println("</html>");

		System.out.println("Arquivo HTML gerado com sucesso!");
	}
	
	/**
	 * Ordena a lista de filmes. Para estudo, implementei duas formas de comparação,
	 * por meio da interface Comparator e da interface Comparable.
	 * 
	 * @param filmes Lista de referências a instâncias de Movie.
	 */
	private void ordenarFilmes(List<Movie> filmes) {
		// Ordenar usando uma classe que implementa a interface Comparator.
		// filmes.sort(new ComparadorFilme());

		// Ordenar usando a ordenação natural definida na própria classe Movie ao
		// implementar a interface Comparable.
		Collections.sort(filmes);
	}
}