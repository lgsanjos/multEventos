package apresentacao;

import java.net.Socket;

import servidor.ClienteListener;
import servidor.Servidor;


public class Principal {
	
	
	private static boolean deveCriarUmServidor(String resposta) {
		return resposta.equalsIgnoreCase("s");
	}
	
	private static void criaCliente(String endereco, int porta) {
		Socket cliente = null;
		try {
			System.out.println(" .. abrindo conexao .. ");
			cliente = new Socket(endereco, porta);
			System.out.println(" .. conexao estabelecida ..");
			
			ClienteListener listener = new ClienteListener(cliente);
			listener.start();
			
			String mensagem = "userName=" + cliente.getLocalAddress();
			cliente.getOutputStream().write(mensagem.getBytes());
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
    private static void defineComportamento() {
    	
    	Servidor servidor;
    	String endereco;
    	int porta;
    	
    	String escolha = MenuSupport.escolhaServidorOuCliente();
    	if (deveCriarUmServidor(escolha)) {
    		porta = MenuSupport.portaDoServidor();
    		servidor = new Servidor(porta);
    		servidor.start();
    		endereco = "127.0.0.1";
    	} else {
    		endereco = MenuSupport.enderecoServidor();
    		porta = MenuSupport.portaDoServidor();
    	}
    	
    	criaCliente(endereco, porta);
    	
    }
    
	public static void main(String args[]) {
		
		System.out.println("*** MultiEventos ***");
		defineComportamento();

		String entrada;
		do {
			entrada = MenuSupport.menuDeOpcoes();
		} while (! entrada.equalsIgnoreCase("s"));

	}
}
