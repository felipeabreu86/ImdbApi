package br.com.alura.imdb;

import java.util.ArrayList;
import java.util.List;

public class ImdbApiResponse {

	// Construtores

	public ImdbApiResponse() {
		super();
	}

	public ImdbApiResponse(int statusCode) {
		setStatusCode(statusCode);
		setErrorMessage("Error " + statusCode);
	}

	// Atributos de instância

	private List<ImdbItem> items = new ArrayList<ImdbItem>();
	private String errorMessage;
	private int statusCode;

	// Getters and Setters

	public List<ImdbItem> getItems() {
		return items;
	}

	public void setItems(List<ImdbItem> items) {
		this.items = items;
	}

	public String getErrorMessage() {
		return errorMessage == null ? new String() : errorMessage.trim();
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	// Métodos

	/**
	 * Indica se houve um erro ao se realizar uma requisição à API do IMDB.
	 * 
	 * @return true em caso de erro e false em caso de resposta válida.
	 */
	public boolean hasError() {
		return !getErrorMessage().isEmpty();
	}

	/**
	 * Retorna uma lista contendo os títulos dos filmes.
	 * 
	 * @return lista com os títulos dos filmes.
	 */
	public List<String> getMoviesTitles() {
		List<String> result = new ArrayList<String>();

		List<ImdbItem> movies = getItems();
		for (int iterator = 0; iterator < movies.size(); iterator++) {
			ImdbItem item = movies.get(iterator);
			result.add(item.getTitle());
		}

		return result;
	}

	/**
	 * Retorna uma lista contendo as URLs das imagens dos filmes.
	 * 
	 * @return lista contendo as URLs das imagens dos filmes.
	 */
	public List<String> getMoviesUrlImages() {
		List<String> result = new ArrayList<String>();

		List<ImdbItem> movies = getItems();
		for (int iterator = 0; iterator < movies.size(); iterator++) {
			ImdbItem item = movies.get(iterator);
			result.add(item.getImage());
		}

		return result;
	}

	/**
	 * Retorna uma lista contendo os anos dos filmes.
	 * 
	 * @return lista contendo os anos dos filmes.
	 */
	public List<String> getMoviesYears() {
		List<String> result = new ArrayList<String>();

		List<ImdbItem> movies = getItems();
		for (int iterator = 0; iterator < movies.size(); iterator++) {
			ImdbItem item = movies.get(iterator);
			result.add(item.getYear());
		}

		return result;
	}

	/**
	 * Retorna uma lista contendo as notas dos filmes.
	 * 
	 * @return lista contendo as notas dos filmes.
	 */
	public List<String> getMoviesRatings() {
		List<String> result = new ArrayList<String>();

		List<ImdbItem> movies = getItems();
		for (int iterator = 0; iterator < movies.size(); iterator++) {
			ImdbItem item = movies.get(iterator);
			result.add(item.getImDbRating());
		}

		return result;
	}
}