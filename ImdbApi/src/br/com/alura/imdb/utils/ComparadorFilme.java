package br.com.alura.imdb.utils;

import java.util.Comparator;

import br.com.alura.imdb.models.Movie;

/**
 * Esta classe implementa a interface Comparator visando criar uma condi��o para
 * a ordena��o da lista de filmes (Movie).
 * 
 * @author Felipe Abreu
 */
public class ComparadorFilme implements Comparator<Movie> {

	/**
	 * Define o Rating como crit�rio de ordena��o para Movie.
	 */
	@Override
	public int compare(Movie filme1, Movie filme2) {
		String rating1 = filme1.getImDbRating();
		String rating2 = filme2.getImDbRating();

		return rating2.compareTo(rating1);
	}
}