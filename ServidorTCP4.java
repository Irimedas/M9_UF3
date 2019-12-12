/**
 * @author Johan y Andreu
 * 
 * */

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServidorTCP4 implements Runnable{
	//Inicializar las variables
	Socket cliente;
	String cadena = "";
	ServerSocket server;
	static int numCliente;

	
	
	public static void main(String[] args) throws IOException {
		int numPort = 60000;
		//Hay que pasar el numero de clientes al inicializar el server
		Scanner teclado = new Scanner(System.in);
		System.out.println("Numero de clientes que soportará el servidro: ");
		int numConexiones = teclado.nextInt();
		ServerSocket servidor = new ServerSocket(numPort);
		Runnable[] arrayRunnable = new Runnable[numConexiones];
		Thread[] arrayThread = new Thread[numConexiones];

		for (int i = 0; i < arrayRunnable.length; i++) {
			Socket clientConnectat = servidor.accept();
			arrayRunnable[i] = new ServidorTCP4(clientConnectat, servidor);
			arrayThread[i] = new Thread(arrayRunnable[i]);
			arrayThread[i].start();
		}

	}
	
	//Constructor
		public ServidorTCP4(Socket clientConnectat, ServerSocket server) {
			this.cliente = clientConnectat;
			this.server = server;
			numCliente ++;
	}
	

	@Override
	public void run() {

		try {
			//FLUX DE SORTIDA AL CLIENT
			PrintWriter fsortida = new PrintWriter(this.cliente.getOutputStream(), true);
			//FLUX D'ENTRADA DEL CLIENT
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));

			fsortida.println("Client: " + this.numCliente);
			
			while ((cadena = fentrada.readLine()) != null) {
				
				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				
				if (cadena.equals("*")){
					break;
				} 
			}

			//TANCAR STREAMS I SOCKETS
			System.out.println("Tancant connexió... ");
			fentrada.close();
			fsortida.close();
			this.cliente.close();
			this.server.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}