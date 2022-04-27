package br.com.alura.imdb.models;

import br.com.alura.imdb.utils.StringUtils;

/**
 * Classe que representa a entidade filme do domínio da aplicação.
 * 
 * @author Felipe Abreu
 */
public class Movie implements Comparable<Movie> {

	// Atributos de instância

	private String title;
	private String year;
	private String image;
	private String imDbRating;

	// Getters and Setters

	/**
	 * Getter do título do filme.
	 * 
	 * @return Título do filme.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter do título do filme.
	 * 
	 * @param title Título do filme.
	 */
	public void setTitle(String title) {
		this.title = StringUtils.isNullOrEmpty(title) ? "" : title.trim();
	}

	/**
	 * Getter do título do filme.
	 * 
	 * @return Ano do filme.
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Setter do ano.
	 * 
	 * @param year Ano do filme.
	 */
	public void setYear(String year) {
		this.year = StringUtils.isNullOrEmpty(year) ? "" : year.trim();
	}

	/**
	 * Getter do título do filme.
	 * 
	 * @return Título do filme.
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Setter da imagem.
	 * 
	 * @param image Imagem do filme.
	 */
	public void setImage(String image) {
		this.image = StringUtils.isNullOrEmpty(image) ? "" : image.trim();
	}

	/**
	 * Getter do rating do filme.
	 * 
	 * @return Rating do filme.
	 */
	public String getImDbRating() {
		return this.imDbRating;
	}

	/**
	 * Setter do rating.
	 * 
	 * @param imDbRating Rating do filme.
	 */
	public void setImDbRating(String imDbRating) {
		this.imDbRating = StringUtils.isNullOrEmpty(imDbRating) ? "" : imDbRating.trim();
	}

	/**
	 * Sobrescrita do método toString().
	 */
	@Override
	public String toString() {
		return String.format("Movie: %s (%s) - Rating: %s", this.title, this.year, this.imDbRating);
	}

	/**
	 * Sobrescrita do método compareTo da interface Comparable para definir que o
	 * Rating será o critério natural de ordenação da classe Movie.
	 */
	@Override
	public int compareTo(Movie anotherMovie) {
		return anotherMovie.getImDbRating().compareTo(this.getImDbRating());
	}
}