package br.com.alura.imdb.api;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;

import br.com.alura.imdb.datas.IHttpClient;
import br.com.alura.imdb.datas.IInternetAvailability;
import br.com.alura.imdb.models.ImdbApiResponse;

/**
 * Classe responsável por realizar requisições à API da IMDB.
 * 
 * @author Felipe Abreu
 */
public class ImdbApiClient implements AutoCloseable {

	// Construtores

	/**
	 * Construtor padrão que cria uma instância da classe ImdbApiRequest utilizando
	 * instâncias de IInternetAvailability e IHttpClient, seguindo o padrão IoC, e
	 * recebendo a credencial de acesso da API.
	 * 
	 * @param apiKey          Credencial de acesso da API do IMDB.
	 * @param internetChecker Instância de IInternetAvailability.
	 * @param httpClient      Instância de IHttpClient.
	 */
	public ImdbApiClient(String apiKey, IInternetAvailability internetChecker, IHttpClient httpClient) {
		this.internetChecker = internetChecker;
		this.httpClient = httpClient;
		this.uri = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;
	}

	// Atributos de instância

	private IInternetAvailability internetChecker;
	private IHttpClient httpClient;
	private String uri;

	/**
	 * Realiza uma requisição do tipo GET à API do IMDB e retorna uma instância da
	 * classe ImdbApiResponse contendo uma lista com os 250 filmes mais bem
	 * avaliados do portal, o Status Code da requisição realizada e, se aplicável,
	 * uma mensagem de erro.
	 * 
	 * @return Instância de ImdbApiResponse.
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public ImdbApiResponse doGetRequest() throws IOException, InterruptedException, URISyntaxException {
		if (!this.internetChecker.isInternetAvailable()) {
			throw new ConnectException("Não há conexão com a Internet.");
		}

		ImdbApiResponse apiResponse = ImdbApiResponse.fromJson(this.httpClient.doGetRequest(uri));
		if (apiResponse.hasError()) {
			throw new ConnectException(apiResponse.getErrorMessage());
		}

		return apiResponse;
	}

	/**
	 * Método chamado ao término da utilização da instância da classe
	 * ImdbApiRequest. Implementação da interface AutoCloseable.
	 */
	@Override
	public void close() {
		internetChecker = null;
		httpClient = null;
		uri = null;
	}
}