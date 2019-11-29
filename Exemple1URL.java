/**
 * Actividad 2Conjunta M9-UF3
 * @author Johan Gonzalez (Xevcan), Andreu Tutusaus (Irimedas)
 * @version 28/11/2019
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
		String param = args[0];
		int port = Integer.parseInt(args[1]);
		int i,j;
		String http =""; 
		String link ="";
		String directory ="";
		
		//Separar la dirección en distintas variables
		try {
			i = param.indexOf(':');
			j = link.indexOf('/');
			
			http = param.substring(0, i);
			link = param.substring(i+3, param.length());
			directory = link.substring(j, link.length());
			link = param.substring(i+3, param.length()-1);
			
//			System.out.println(http + "|" + link + "|" + directory);
					
		}  catch (Exception e) {
			System.err.println("URL mal introducida.    Ejemplo: (https://github.com)");
		}
		 
		//Comprovar que la URL sea correcta, y que el constructor
		//construya de forma correcta
		try {	
			url = new URL(http, link, port, directory);
			getURLSauce(url);
			
		} catch (MalformedURLException e) {
			System.err.println("Puerto o Link incorrecto.");
		}
	}
	
	/**
	 * Pasamos la URL y solicitamos el HTML de la web
	 * @param url URL
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

//	private static void Visualitzar(URL url) {
//		
//		System.out.println("\tURL complerta: "+url.toString());
//		System.out.println("\tgetProtocol: "+url.getProtocol());
//		System.out.println("\tgetHost: "+url.getHost());
//		System.out.println("\tgetPort: "+url.getPort());
//		System.out.println("\tgetFile: "+url.getFile());
//		System.out.println("\tgetUserInfo: "+url.getUserInfo());
//		System.out.println("\tgetPath: "+url.getPath());
//		System.out.println("\tgetAuthority: "+url.getAuthority());
//		System.out.println("\tgetQuery: "+url.getQuery());
//		System.out.println("=====================================================");
//	}

}
