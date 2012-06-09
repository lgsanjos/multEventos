package apresentacao;

import java.net.InetAddress;
import java.rmi.Naming;

import negocio.Usuario;

import compartilhado.ClientInterector;
import compartilhado.ServerInterector;
import compartilhado.ServerInterectorInterface;

public class Principal {
	
	private static ServerInterectorInterface ServerInterector = null;
	private static Usuario usuario = null;
	
	public static ServerInterectorInterface getServidor() {
		return ServerInterector;
	}
	
	private static boolean deveCriarUmServidor(String resposta) {
		return resposta.equalsIgnoreCase("s");
	}
	
	public static Usuario getUsuario() {
		return usuario;
	}
	
    private static void defineComportamento() throws Exception {
    	
    	String escolha = MenuSupport.escolhaServidorOuCliente();
    	String endereco;

    	if (deveCriarUmServidor(escolha)) {
    		ServerInterector = (ServerInterectorInterface) new ServerInterector();
    		endereco = "//127.0.0.1/multieventos";
    		Naming.bind(endereco, ServerInterector);
    	} else {
    		endereco = MenuSupport.enderecoServidor() + "/multieventos";
    		if (!endereco.startsWith("//"))
    			endereco = "//" + endereco;
    		ServerInterector = (ServerInterectorInterface) Naming.lookup(endereco);
    	}
    	
    	criaERegistraCliente();
    }
    
    private static void criaERegistraCliente() throws Exception {
    	
    	usuario = MenuSupport.criaUsuario();
    	String endereco = "//" + InetAddress.getLocalHost().getHostName() + "/" + usuario.getNome();
    	
    	ClientInterector clienteInterector = new ClientInterector();
    	Naming.bind(endereco, clienteInterector);
    	ServerInterector.registerClientInterector(usuario.getNome(), endereco);
    }
    
	public static void main(String args[]) throws Exception {
		System.out.println("*** MultiEventos ***");
		defineComportamento();

		String entrada;
		do {
			entrada = MenuSupport.menuDeOpcoes();
		} while (! entrada.equalsIgnoreCase("s"));

	}
}
