/**
 * @author Andreu Tutusuas y Johan Gonzalez
 */

import java.net.*;
import java.io.*;

public class ClientUDP2{
	
	public static void main (String[] args) throws Exception{
		
		//FLUX PER A ENTRADA ESTÀNDARD
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//Socket client
		
		DatagramSocket clientSocket = new DatagramSocket();
		byte[] enviats = new byte[1024];
		byte[] rebuts = new byte[1024];
		
		//DADES DEL SERVIDOR al qual s'envia el missatge
		InetAddress IPServidor = InetAddress.getLocalHost();
		int port = 9800;
		String cadena = "";
		
		//Bucle que detecta, según el texto intrducido, si se puede
		//cerar el cliente mediante la string "exit"
		while(!cadena.equalsIgnoreCase("exit")) {
			
			//INTRODUIR DADES PEL TECLAT
			System.out.println("----------------------------------------");
			System.out.println("Introdueix missatge:('exit' -> salir)");
			cadena = in.readLine();
			enviats = cadena.getBytes();
			
			if(cadena.equalsIgnoreCase("exit")){
				
				System.out.println("Hasta la vista Baby.");
				
			}else {
			
				//ENVIANT DATAGRAMA AL SERVIDOR
				System.out.println("Enviant "+enviats.length+"bytes al servidor.");
				DatagramPacket enviament = new DatagramPacket(enviats, enviats.length, IPServidor, port);
				clientSocket.send(enviament);
				
				//Comprovamos el tiempo de espera para detectar conexión mediante
				//el ".setSoTimeout(5000)", aplicando un try catch en el caso de que 
				//pase el tiempo de espera de la conexión
				clientSocket.setSoTimeout(5000);
				
				try {
				
					//REBENT DATAGRAMA DEL SERVIDOR
					DatagramPacket rebut = new DatagramPacket(rebuts, rebuts.length);
					System.out.println("Esperant datagrama...");
					clientSocket.receive(rebut);
					String majuscula = new String(rebut.getData());
					
					//ACONSEGUINT INFORMACIÓ DEL DATAGRAMA
					InetAddress IPOrigen = rebut.getAddress();
					int portOrigen = rebut.getPort();
					System.out.println("\tProcedent de: "+IPOrigen+":"+portOrigen);
					System.out.println("\tDades: "+majuscula.trim());
					
				//Caso detectado cuando pasa el tiempo de espera para la conexión
				}catch(SocketTimeoutException e) {
					
					System.out.println("Conexión incorrecta con el servidor.");
					System.out.println("Cerrando petición.");
					cadena = "exit";
					
				}
			}
			
		}
		
		//Tanca el socket
		clientSocket.close();
		
	}

}

