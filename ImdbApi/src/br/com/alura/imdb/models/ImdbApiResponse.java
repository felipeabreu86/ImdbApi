package br.com.alura.imdb.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import br.com.alura.imdb.utils.StringUtils;

/**
 * Classe respons�vel por armazenar a resposta da API do IMDB � requisi��o
 * realizada.
 * 
 * @author Felipe Abreu
 */
public class ImdbApiResponse {

	// Construtores

	/**
	 * Construtor respons�vel por criar uma refer�ncia padr�o da classe
	 * ImdbApiResponse.
	 */
	public ImdbApiResponse() {
		super();
		items = new ArrayList<Movie>();
	}

	/**
	 * Construtor respons�vel por criar uma refer�ncia da classe ImdbApiResponse com
	 * uma mensagem de erro configurada.
	 * 
	 * @param errorMessage Mensagem de erro referente � requisi��o feita � API.
	 */
	public ImdbApiResponse(String errorMessage) {
		this();
		Objects.requireNonNull(errorMessage);
		setErrorMessage(errorMessage);
	}

	// Atributos de inst�ncia

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

	// M�todos F�brica (Factory Method)

	/**
	 * M�todo f�brica da classe ImdbApiResponse que retorna uma inst�ncia criada a
	 * partir do mapeamento de um Json recebido por par�metro.
	 * 
	 * @param json Mapeamento da classe ImdbApiResponse no padr�o Json.
	 * @return Inst�ncia da classe ImdbApiResponse.
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

	// M�todos

	/**
	 * Este m�todo serializa a inst�ncia da classe em sua representa��o Json
	 * equivalente.
	 * 
	 * @return Representa��o Json da inst�ncia de ImdbApiResponse.
	 */
	public String toJson() {
		return new Gson().toJson(this);
	}

	/**
	 * Este m�todo indica se houve um erro ao se realizar uma requisi��o � API do
	 * IMDB.
	 * 
	 * @return true em caso de erro e false em caso de resposta v�lida.
	 */
	public boolean hasError() {
		return !getErrorMessage().isEmpty();
	}
}