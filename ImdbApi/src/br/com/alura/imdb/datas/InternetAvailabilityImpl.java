package br.com.alura.imdb.datas;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Classe responsável por implementar a interface IInternetAvailability.
 * 
 * @author Felipe Abreu
 */
public class InternetAvailabilityImpl implements IInternetAvailability {

	/**
	 * Retorna se a Internet está disponível.
	 * 
	 * @return True para Internet disponível e False para indisponível.
	 */
	public boolean isInternetAvailable() {
		return isHostAvailable("google.com") || isHostAvailable("amazon.com") || isHostAvailable("facebook.com")
				|| isHostAvailable("apple.com");
	}

	/**
	 * Realiza o teste de conexão.
	 * 
	 * @param hostName Host a ser utilizado na verificação da Internet.
	 * @return True para Internet disponível e False para indisponível.
	 */
	private static boolean isHostAvailable(String hostName) {
		try (Socket socket = new Socket()) {
			int port = 80;
			InetSocketAddress socketAddress = new InetSocketAddress(hostName, port);
			socket.connect(socketAddress, 3000);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
}