package servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class Servidor extends Thread {
	
	private int porta;
	
	public Servidor(int porta) {
		super();
		this.porta = porta;
	}
	
	public void run() {
		
		ServerSocket socket;
		try {
			socket = new ServerSocket(porta);
			while (true) {
				Socket clienteSocket = socket.accept();
				System.out.println("  .. cliente conectado ..");
				
				ClientesManager.getInstance().addCliente(clienteSocket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
