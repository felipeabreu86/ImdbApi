package br.com.alura.imdb.utils;

/**
 * Classe responsável por implementar métodos úteis para a classe String.
 * 
 * @author Felipe Abreu
 */
public class StringUtils {

	/**
	 * Construtor privado.
	 */
	private StringUtils() {
		super();
	}

	/**
	 * Retorna se o parâmetro é nulo ou vazio.
	 * 
	 * @param param Texto.
	 * @return True para texto nulo ou vazio e False para caso contrário.
	 */
	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}
}