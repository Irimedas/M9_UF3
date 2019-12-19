/**
 * @author Johan y Andreu
 * 
 * */

import java.net.*;
import javax.swing.text.StyledEditorKit.BoldAction;
import java.io.*;

public class ClientTCP2_V2 {

	public static void main (String[] args) throws Exception {

		String host = "localhost";
		int port = 60000;//Port remot
		Socket client = null;
		
		//Try de control de connexiones con el servidor
		try {
			System.out.println("Intentando conectarse con el servidor...");
			client = new Socket(host, port);
			client.setSoTimeout(5000);
			
			//Try de control de respuesta del servidor
			try {
				
				//FLUX DE SORTIDA AL SERVIDOR
				PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);

				//FLUX D'ENTRADA AL SERVIDOR
				BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));

				//FLUX PER A ENTRADA ESTÀNDARD
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				String cadena, eco, eco2 = "";
				
				System.out.println("Conexión con el servidor establecida.\n"
								 + "----------------------\n"
								 + fentrada.readLine() + "\n"
						 		 + "Introdueix la cadena: ");
				
				//Lectura teclat
				cadena = in.readLine();

				boolean b = true;
				while (b && cadena != null) {
					
					//Detección de salida por teclado
					if(cadena.equalsIgnoreCase("quit")){
						b = false;
						System.out.println("Cortando conexión...\n"
										 + "Bye ;)");
					}
					
					//Enviament cadena al servidor
					fsortida.println(cadena);
					
					if(b) {
						//Rebuda cadena del servidor
						eco = fentrada.readLine();
						eco2 = fentrada.readLine();
						System.out.println("=>ID: " + eco + "\n"
										 + "=>ECO: " + eco2 + "\n"
										 + "---------------------");
						//Lectura del teclat
						cadena = in.readLine();
					
					}else {
						//Enviament cadena al servidor -> sortida de client
						fsortida.println("Clinte desconectado X");
					}
				}

				fsortida.close();
				fentrada.close();
				in.close();
				client.close();
			
			}catch (SocketTimeoutException e) {
				System.out.println("Conexión incorrecta con el servidor.");
				System.out.println("Cerrando conexión.");
				client.close();
			}
			
		} catch (ConnectException e) {
			System.out.println("El servidor al que te intentas conectar no acepta más peticiones.\n"
							 + "Cancelando petición.");
			
		}
	}
} 