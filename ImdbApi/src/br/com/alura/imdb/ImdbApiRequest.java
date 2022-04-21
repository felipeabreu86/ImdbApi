/**
 * 
 */
package br.com.alura.imdb;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author Felipe Abreu
 * @version 1.0
 */
public class ImdbApiRequest {

	/**
	 * 
	 * @param args
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

		HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

		HttpRequest request = HttpRequest.newBuilder()
				.uri(new URI("https://imdb-api.com/en/API/Top250Movies/" + ApiKey.imdbKey)).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		System.out.println(response.statusCode());
		System.out.println(response.body());
	}
}