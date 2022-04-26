package br.com.alura.imdb.datas;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.ServerException;
import java.util.Objects;

public class HttpClientImpl implements IHttpClient {

	// Construtores

	/**
	 * Construtor que recebe uma instância de cliente HTTP.
	 * 
	 * @param httpClient Instância do cliente HTTP.
	 */
	public HttpClientImpl(HttpClient httpClient) {
		Objects.requireNonNull(httpClient);
		this.httpClient = httpClient;
	}

	// Atributos

	private HttpClient httpClient;

	// Métodos de instância.

	/**
	 * Método responsável por realizar a requisição à API.
	 * 
	 * @param uri Identificador uniforme de recurso da API.
	 * @throws IOException
	 * @throws InterruptedException
	 * @throws URISyntaxException
	 * @return Retorna o corpo da resposta.
	 */
	@Override
	public String doGetRequest(String uri) throws IOException, InterruptedException, URISyntaxException {
		Objects.requireNonNull(uri);
		String responseBody = null;

		HttpRequest request = HttpRequest.newBuilder().uri(new URI(uri)).GET().build();
		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() == 200) {
			responseBody = response.body();
		} else {
			throw new ServerException("Status Code Error " + response.statusCode());
		}

		return responseBody;
	}
}