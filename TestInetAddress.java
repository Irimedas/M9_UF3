import java.net.*;

public class TestInetAddress {
	
	public static void main (String[] args) {
		
		String parametro_1 = args[0]; 
		
		InetAddress dir = null;
		System.out.println("=====================================================");
		System.out.println("SORTIDA PER A LOCALHOST");
		
		try {
			//LOCALHOST
			dir = InetAddress.getByName(parametro_1);
			//dir = InetAddress.getByName("DESKTOP-J4T1S4E");
			provaTots(dir);
			
			//URL www.google.com
			System.out.println("=====================================================");
//			System.out.println("SORTIDA PER A URL");
//			dir = InetAddress.getByName("www.insbaixcamp.cat");
//			provaTots(dir);
//			
//			//Array tipus InetAddress amb totes les adreces IP de google.com
//			System.out.println("\tAdreces IP per a: "+dir.getHostName());
//			InetAddress[] adreces = InetAddress.getAllByName(dir.getHostName());
//			for (int i=0; i<adreces.length; i++)
//				System.out.println("\t\t"+adreces[i].toString());
//			System.out.println("=====================================================");
			
		} catch (UnknownHostException e1) {e1.printStackTrace();}
		
	}
	
	private static void provaTots(InetAddress dir) {
		
		InetAddress dir2;
		System.out.println();
		
		//Lo mismo que el "to String()"
		System.out.println("\tMètode getByName(): "+dir);
		
		try {
			dir2 = InetAddress.getLocalHost();
			
			//getLocalHost:
			//Nombre canonico de la maquina host / ip de la maquina host
			System.out.println("\tMètode getLocalHost(): "+dir2);
		} catch (UnknownHostException e) {e.printStackTrace();}
		
		//getHostName:
		//Nombre canonico de la maquina host
		System.out.println("\tMètode getHostName(): "+dir.getHostName());
		
		//getHostAddress:
		//retorna la ip de la maquina a la qual se hace la petición 
		System.out.println("\tMètode getHostAddress(): "+dir.getHostAddress());
		
		//toString:
		//Nombre canonico de la maquina / ip de la maquina 
		System.out.println("\tMètode toString(): "+dir.toString());
		
		//getCanonicalHostName:
		//Si la primera consulta hacia una maquina fue mediante su ip, va a recordar esta 
		//ip como su nombre canonico en cache. En el momento que, haciendo una petición
		//por nombre de maquina, esta detecta una ip previamente utilizada, recordara este
		//nombre como canonico de la maquina.
		System.out.println("\tMètode getCanonicalHostName(): "+dir.getCanonicalHostName());
		
	}

}
