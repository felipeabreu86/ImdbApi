package br.com.alura.imdb;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;

/**
 * Classe que trata da requisição à API da IMDB
 * 
 * @author Felipe Abreu
 * @version 1.0
 */
public class ImdbApiRequest {

	/**
	 * Inicializa a aplicação.
	 * 
	 * @param args
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) {
		try {
			ImdbApiResponse response = apiRequest();
			showResult(response);
		} catch (URISyntaxException | IOException | InterruptedException | NullPointerException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Exibe no console o resultado da consulta à API, mostrando os títulos, URLs
	 * das imagens, ano dos filmes e notas.
	 * 
	 * @param response
	 * @throws NullPointerException
	 */
	private static void showResult(ImdbApiResponse response) throws NullPointerException {
		Objects.requireNonNull(response);

		List<String> titles = response.getMoviesTitles();
		List<String> urlImages = response.getMoviesUrlImages();
		List<String> years = response.getMoviesYears();
		List<String> ratings = response.getMoviesRatings();

		titles.forEach(System.out::println);
		urlImages.forEach(System.out::println);
		years.forEach(System.out::println);
		ratings.forEach(System.out::println);
	}

	/**
	 * Realiza uma requisição à API do IMDB e exibe no terminal os 250 filmes mais
	 * bem avaliados.
	 * 
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static ImdbApiResponse apiRequest() throws URISyntaxException, IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("https://imdb-api.com/en/API/Top250Movies/" + ApiKey.imdbKey)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		ImdbApiResponse imdbApiResponse = new Gson().fromJson(response.body(), ImdbApiResponse.class);
		imdbApiResponse.setStatusCode(response.statusCode());

		return imdbApiResponse;
	}
}