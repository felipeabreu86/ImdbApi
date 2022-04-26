package br.com.alura.imdb.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.util.Objects;

import br.com.alura.imdb.api.ImdbApiClient;
import br.com.alura.imdb.datas.HttpClientImpl;
import br.com.alura.imdb.datas.IHttpClient;
import br.com.alura.imdb.datas.IInternetAvailability;
import br.com.alura.imdb.datas.InternetAvailabilityImpl;
import br.com.alura.imdb.models.ImdbApiResponse;
import br.com.alura.imdb.utils.ApiKey;
import br.com.alura.imdb.utils.HTMLGenerator;

/**
 * Classe respons�vel por inicializar a aplica��o IMDB por meio do m�todo
 * main().
 * 
 * @author Felipe Abreu
 * @version 1.0
 */
public class ImdbMain {

	/**
	 * Inicializa a aplica��o, a requisi��o � API e a gera��o do arquivo HTML.
	 * 
	 * @param args Array que recebe dados (Strings) da linha de comando.
	 */
	public static void main(String[] args) {
		try {
			ImdbApiResponse response = getImdbApiResponse();
			generateHtmlFile(response);
		} catch (IOException | InterruptedException | URISyntaxException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			System.out.println("Erro inesperado!");
			ex.printStackTrace();
		}
	}

	/**
	 * M�todo de classe que retorna a resposta da consulta � API do IMDB.
	 * 
	 * @return Inst�ncia da classe ImdbApiResponse que corresponde a resposta da
	 *         consulta � API do IMDB.
	 * @throws URISyntaxException   Exce��o verificada lan�ada para indicar que uma
	 *                              string n�o p�de ser analisada como uma
	 *                              refer�ncia de URI.
	 * @throws InterruptedException Lan�ado quando um encadeamento est� esperando,
	 *                              dormindo ou ocupado de outra forma, e o
	 *                              encadeamento � interrompido, antes ou durante a
	 *                              atividade.
	 * @throws IOException          Indica que ocorreu uma exce��o de I/O de algum
	 *                              tipo. Essa classe � a classe geral de exce��es
	 *                              produzidas por opera��es de I/O com falha ou
	 *                              interrompidas.
	 */
	private static ImdbApiResponse getImdbApiResponse() throws IOException, InterruptedException, URISyntaxException {
		IInternetAvailability internetChecker = new InternetAvailabilityImpl();
		IHttpClient httpClient = new HttpClientImpl(HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build());
		ImdbApiResponse response = null;

		try (ImdbApiClient api = new ImdbApiClient(ApiKey.imdbKey, internetChecker, httpClient)) {
			response = api.doGetRequest();
		}

		return response;
	}

	/**
	 * M�todo de classe que gera um arquivo HTML a partir da lista de filmes.
	 * 
	 * @param response Inst�ncia da classe ImdbApiResponse que corresponde a
	 *                 resposta da consulta � API do IMDB.
	 */
	private static void generateHtmlFile(ImdbApiResponse response) {
		Objects.requireNonNull(response);

		PrintWriter writer = null;
		try {
			writer = new PrintWriter("movies.html");
			HTMLGenerator htmlGenerator = new HTMLGenerator(writer);
			htmlGenerator.generate(response.getItems());
		} catch (FileNotFoundException | SecurityException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}