package br.com.alura.imdb.utils;

/**
 * Classe respons�vel por implementar m�todos �teis para a classe String.
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
	 * Retorna se o par�metro � nulo ou vazio.
	 * 
	 * @param param Texto.
	 * @return True para texto nulo ou vazio e False para caso contr�rio.
	 */
	public static boolean isNullOrEmpty(String param) {
		return param == null || param.trim().length() == 0;
	}
}