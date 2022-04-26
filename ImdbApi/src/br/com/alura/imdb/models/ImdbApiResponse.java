package br.com.alura.imdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.alura.imdb.utils.StringUtils;

/**
 * Classe responsável por armazenar a resposta da API do IMDB à requisição
 * realizada.
 * 
 * @author Felipe Abreu
 */
public class ImdbApiResponse {

	// Construtores

	/**
	 * Construtor responsável por criar uma referência padrão da classe
	 * ImdbApiResponse.
	 */
	public ImdbApiResponse() {
		super();
		items = new ArrayList<Movie>();
	}

	/**
	 * Construtor responsável por criar uma referência da classe ImdbApiResponse com
	 * uma mensagem de erro configurada.
	 * 
	 * @param errorMessage Mensagem de erro referente à requisição feita à API.
	 */
	public ImdbApiResponse(String errorMessage) {
		this();
		Objects.requireNonNull(errorMessage);
		setErrorMessage(errorMessage);
	}

	// Atributos de instância

	private List<Movie> items;
	private String errorMessage;

	// Getters and Setters

	/**
	 * Getter do atributo items.
	 * 
	 * @return Lista de filmes (Movie).
	 */
	public List<Movie> getItems() {
		return items;
	}

	/**
	 * Setter do atributo items.
	 * 
	 * @param items Lista de filmes (Movie).
	 */
	public void setItems(List<Movie> items) {
		this.items = items;
	}

	/**
	 * Getter do atributo errorMessage.
	 * 
	 * @return Mensagem de erro.
	 */
	public String getErrorMessage() {
		return errorMessage == null ? "" : errorMessage;
	}

	/**
	 * Setter do atributo errorMessage,
	 * 
	 * @param errorMessage Mensagem de erro.
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = StringUtils.isNullOrEmpty(errorMessage) ? "" : errorMessage.trim();
	}

	// Métodos Fábrica (Factory Method)

	/**
	 * Método fábrica da classe ImdbApiResponse que retorna uma instância criada a
	 * partir do mapeamento de um Json recebido por parâmetro.
	 * 
	 * @param json Mapeamento da classe ImdbApiResponse no padrão Json.
	 * @return Instância da classe ImdbApiResponse.
	 */
	public static ImdbApiResponse fromJson(String json) {
		Objects.requireNonNull(json);

		ImdbApiResponse imdbApiResponse;
		try {
			imdbApiResponse = new Gson().fromJson(json, ImdbApiResponse.class);
		} catch (JsonSyntaxException ex) {
			imdbApiResponse = new ImdbApiResponse(ex.getMessage());
		}

		return imdbApiResponse;
	}

	// Métodos

	/**
	 * Este método serializa a instância da classe em sua representação Json
	 * equivalente.
	 * 
	 * @return Representação Json da instância de ImdbApiResponse.
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}

	/**
	 * Este método indica se houve um erro ao se realizar uma requisição à API do
	 * IMDB.
	 * 
	 * @return true em caso de erro e false em caso de resposta válida.
	 */
	public boolean hasError() {
		return !getErrorMessage().isEmpty();
	}
}