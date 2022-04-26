package br.com.alura.imdb.datas;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IHttpClient {
	public String doGetRequest(String uri) throws IOException, InterruptedException, URISyntaxException;
}