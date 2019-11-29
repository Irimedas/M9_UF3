/**
 * M9-UF3
 * Actividad 2 Conjunta
 * 
 * @author Johan Gonzalez (Xevcan), Andreu Tutusaus (Irimedas)
 * @version 29/11/2019
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Scanner;

public class Exemple1URL {
	
	public static void main (String[] args) {
		
		//Inicialización de variables
		URL url = null;
		URL new_url = null;
		String url_str = "";
		int port = 0;
		
//		url_str = "http://www.catastro.meh.es/";
//		port = 80;
		
		
		//Comprobamos lo parametros pasados de forma basica para
		//asegurarnos de que no falte ninguno
		try {
			url_str = args[0];
			port = Integer.parseInt(args[1]);
		}catch (Exception e) {
			System.out.println("parametro 1 -> url\n"+
					   "parametro 1 -> puerto\n");
		}
		
		try {	
			url = new URL(url_str);
//			Visualitzar(url, port);
			new_url = transformURL(url, port);
			getURLSauce(new_url);
			
		} catch (MalformedURLException e) {
			System.err.println("Puerto o Link incorrecto.");
		}
	}
	
	/**
	 * Pasamos la URL y solicitamos el HTML de la web
	 */
	public static void getURLSauce(URL url) {
		
		BufferedReader in;
		
		try {
			InputStream inputStream = url.openStream();
			in = new BufferedReader(new InputStreamReader(inputStream));
			
			String inputLine;
			
			while ((inputLine = in.readLine()) != null)
				System.out.println(inputLine);
			in.close();
			
		} catch (IOException e) {e.printStackTrace(); }
	}
	
	/**
	 * Transformamos la url que nos pasan por parametros
	 * en una url que utilize el puerto que definimos por parametros
	 */
	private static URL transformURL(URL url, int port) throws MalformedURLException{
		String url_str = 
				url.getProtocol() + "://" + url.getHost() + ":" + port + url.getPath();
		
		System.out.println("Resultado ->> " + url_str);
		URL new_url = new URL(url_str);
		return new_url;	
	}
	
	/**
	 * Vemos una comparativa de parametros entre la url pasada
	 * por parametros y la url generada con el puerto.
	 */
	private static void Visualitzar(URL url, int port) throws MalformedURLException {
		
		System.out.println("=====================================================");
		System.out.println("\tURL complerta: "+url.toString());
		System.out.println("\tgetProtocol: "+url.getProtocol());
		System.out.println("\tgetHost: "+url.getHost());
		System.out.println("\tgetPort: "+url.getPort());
		System.out.println("\tgetFile: "+url.getFile());
		System.out.println("\tgetUserInfo: "+url.getUserInfo());
		System.out.println("\tgetPath: "+url.getPath());
		System.out.println("\tgetAuthority: "+url.getAuthority());
		System.out.println("\tgetQuery: "+url.getQuery());
		System.out.println("=====================================================");
		
		URL url2 = transformURL(url, port);
		
		System.out.println("=====================================================");
		System.out.println("\tURL complerta: "+url2.toString());
		System.out.println("\tgetProtocol: "+url2.getProtocol());
		System.out.println("\tgetHost: "+url2.getHost());
		System.out.println("\tgetPort: "+url2.getPort());
		System.out.println("\tgetFile: "+url2.getFile());
		System.out.println("\tgetUserInfo: "+url2.getUserInfo());
		System.out.println("\tgetPath: "+url2.getPath());
		System.out.println("\tgetAuthority: "+url2.getAuthority());
		System.out.println("\tgetQuery: "+url2.getQuery());
		System.out.println("=====================================================");	
	}
}
