/**
 * @author Johan y Andreu
 * 
 * */

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServidorTCP4_V2 implements Runnable{
	//Inicializar las variables
	Socket cliente;
	String cadena = "";
	static ServerSocket server;
	private int identificador;
	static int numCliente;
	
	static Thread[] arrayThread = null;
	static Runnable[] arrayRunnable = null;
	
	//Variable que detecta si el servidor esta cerrado
	static boolean sClosed = false;

	public static void main(String[] args) throws IOException {
		int numPort = 60000;
		
		//Hay que pasar el numero de clientes al inicializar el server
		Scanner teclado = new Scanner(System.in);
		System.out.println("Numero de clientes que soportará el servidor: ");
		int numConexiones = teclado.nextInt();
		ServerSocket servidor = new ServerSocket(numPort);

		arrayRunnable = new Runnable[numConexiones];
		arrayThread = new Thread[numConexiones];
		
		System.out.println("------Servidor en funcionamiento------------");
		
		System.out.println("|> " + arrayRunnable.length);
		
		for (int i = 0; i < arrayRunnable.length; i++) {
			crearSocketHabilitado(servidor, i);
		}
	}

	//Constructor
	public ServidorTCP4_V2(Socket clientConnectat, ServerSocket server, int identificador) {
		this.cliente = clientConnectat;
		ServidorTCP4_V2.server = server;
		this.identificador = numCliente + 1;
		numCliente ++;
		
		System.out.println("DEV> Constructor");
	}

	//Función "Runable" del codigo
	@Override
	public void run() {
		
		try {
			//Flujo de entrada del cliente
			BufferedReader fentrada = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));
			
			//Flujo de salida al cliente
			PrintWriter fsortida = new PrintWriter(this.cliente.getOutputStream(), true);
			fsortida.println("Client: " + this.identificador);

			boolean b = true;
			sClosed = false;
			cadena = fentrada.readLine();
			
			//Bucle de control de lecturas de entrada
			while (!sClosed && b && 
					((cadena != null) || (!cadena.equalsIgnoreCase("quit")))) {
				
				fsortida.println(this.identificador);
				fsortida.println(cadena);
				System.out.println("Recibiendo datos...\n"
								 + "=> Cliente: " + identificador + "\n"
								 + "=> Mensaje: " + cadena + "\n"
								 + "---------------------");
				
				//Si un cliente se sale del servidor de forma normal
				if (cadena.equalsIgnoreCase("quit")){
					System.out.println("Cortando la conexión con el cliente " + identificador + "...\n"
									 + "Bye ;)\n"
									 + "---------------------");
					b = false;
					
					//buscar metodo para cerrar hilo despues de run
					//arrayThread[1].
				
				//Si se quiere cerrar el servidor
				}else if(cadena.equalsIgnoreCase("*")) {
					System.out.println("Cerrando sevidor...\n"
							 		 + "Bye ;)\n"
							 		+ "---------------------");
					b = false;
					sClosed = true;
				
				//en cualquier otro caso
				}else {
					cadena = fentrada.readLine();	
				}
			}

			//Cerrar todo lo que conlleva a la actividad del servidor
			System.out.println("I> Cerrando hilo " + identificador + "...\n"
							 + "---------------------");
			
			if(sClosed) {
				fentrada.close();
				fsortida.close();
				this.server.close();
				this.cliente.close();
			} else {
				fentrada.close();
				fsortida.close();
				this.cliente.close();
			}
			
			
		} catch (SocketException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Crea sockets para el servidor y la posición definidos
	public static void crearSocketHabilitado(ServerSocket servidor, int ident) throws IOException{
		Socket clientConnectat = servidor.accept();
		arrayRunnable[ident] = new ServidorTCP4_V2(clientConnectat, servidor, ident);
		arrayThread[ident] = new Thread(arrayRunnable[ident]);
		arrayThread[ident].start();
	}
} 