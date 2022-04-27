package br.com.alura.imdb.api;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;

import br.com.alura.imdb.datas.IHttpClient;
import br.com.alura.imdb.datas.IInternetAvailability;
import br.com.alura.imdb.models.ImdbApiResponse;

/**
 * Classe respons�vel por realizar requisi��es � API da IMDB.
 * 
 * @author Felipe Abreu
 */
public class ImdbApiClient implements AutoCloseable {

	// Construtores

	/**
	 * Construtor padr�o que cria uma inst�ncia da classe ImdbApiRequest utilizando
	 * inst�ncias de IInternetAvailability e IHttpClient, seguindo o padr�o IoC, e
	 * recebendo a credencial de acesso da API.
	 * 
	 * @param apiKey          Credencial de acesso da API do IMDB.
	 * @param internetChecker Inst�ncia de IInternetAvailability.
	 * @param httpClient      Inst�ncia de IHttpClient.
	 */
	public ImdbApiClient(String apiKey, IInternetAvailability internetChecker, IHttpClient httpClient) {
		this.internetChecker = internetChecker;
		this.httpClient = httpClient;
		this.uri = "https://imdb-api.com/en/API/Top250Movies/" + apiKey;
	}

	// Atributos de inst�ncia

	private IInternetAvailability internetChecker;
	private IHttpClient httpClient;
	private String uri;

	/**
	 * Realiza uma requisi��o do tipo GET � API do IMDB e retorna uma inst�ncia da
	 * classe ImdbApiResponse contendo uma lista com os 250 filmes mais bem
	 * avaliados do portal, o Status Code da requisi��o realizada e, se aplic�vel,
	 * uma mensagem de erro.
	 * 
	 * @return Inst�ncia de ImdbApiResponse.
	 * @throws URISyntaxException
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public ImdbApiResponse doGetRequest() throws IOException, InterruptedException, URISyntaxException {
		if (!this.internetChecker.isInternetAvailable()) {
			throw new ConnectException("N�o h� conex�o com a Internet.");
		}

		ImdbApiResponse apiResponse = ImdbApiResponse.fromJson(this.httpClient.doGetRequest(uri));
		if (apiResponse.hasError()) {
			throw new ConnectException(apiResponse.getErrorMessage());
		}

		return apiResponse;
	}

	/**
	 * M�todo chamado ao t�rmino da utiliza��o da inst�ncia da classe
	 * ImdbApiRequest. Implementa��o da interface AutoCloseable.
	 */
	@Override
	public void close() {
		internetChecker = null;
		httpClient = null;
		uri = null;
	}
}