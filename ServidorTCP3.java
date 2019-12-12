/**
 * @author Johan y Andreu
 * 
 * */


import java.net.*;
import java.io.*;

public class ServidorTCP3 implements Runnable{
	//Inicializar las variables
	private Socket cliente;
	private String cadena = "";
	private ServerSocket server;
	private static int numCliente;

	public static void main(String[] args) throws IOException {
		int numPort = 60000;
		ServerSocket servidor = new ServerSocket(numPort);
		//El server soportar� hasta 5 clientes conectados al mismo tiempo
		Runnable[] arrayRunnable = new Runnable[5];
		Thread[] arrayThread = new Thread[5];

		for (int i = 0; i < arrayRunnable.length; i++) {
			Socket clientConnectat = servidor.accept();
			arrayRunnable[i] = new ServidorTCP3(clientConnectat, servidor);
			arrayThread[i] = new Thread(arrayRunnable[i]);
			arrayThread[i].start();
		}
	}
	
	//Constructor
		public ServidorTCP3(Socket clientConnectat, ServerSocket server) {
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
			fsortida.println("Client " + this.numCliente + ".");
			
			while ((cadena = fentrada.readLine()) != null) {
				
				fsortida.println(cadena);
				System.out.println("Rebent: "+cadena);
				
				if (cadena.equals("*")){
					break;
				} 
			}

			//TANCAR STREAMS I SOCKETS
			System.out.println("Tancant connexi�... ");
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