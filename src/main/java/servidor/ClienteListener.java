package servidor;

import java.net.Socket;

public class ClienteListener extends Thread {
	
	private Socket conexao;

	public ClienteListener(Socket conexao) {
		this.conexao = conexao; 
	}
	
	public void run() {
		
		StringBuffer mensagem = new StringBuffer();
		
		while (!conexao.isClosed()) {
			try {

				while (conexao.getInputStream().available() > 0) {
					mensagem.append( (char) conexao.getInputStream().read());
				}
				
				if (mensagem.length() > 0) {
					System.out.println(new String(mensagem));
					mensagem = new StringBuffer();
				}	
				
				sleep(1000L);
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
			
		}
	}
	
	
}
