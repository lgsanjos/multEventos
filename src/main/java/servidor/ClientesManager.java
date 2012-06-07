package servidor;

import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class ClientesManager {
	
	private static ClientesManager instancia;
	private LinkedList<Cliente> clientes;
	
	private ClientesManager() {
		clientes = new LinkedList<Cliente>();
	}
	
	public static ClientesManager getInstance() {
		if (instancia == null)
			instancia = new ClientesManager();
			
		return instancia;
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public Cliente addCliente(Socket socket) {
		Cliente cliente = new Cliente(socket);
		cliente.start();
		addCliente(cliente);

		return cliente;
	}
	
	public void addCliente(Cliente novoCliente) {
		System.out.println("Novo cliente se conectou");
		clientes.add(novoCliente);
	}
	
	public void enviarMensagem(String mensagem, String sender) {
		
		for (Cliente cliente : clientes) {
			if (!cliente.getNome().equalsIgnoreCase(sender))
				cliente.enviarMensagem(mensagem, sender);
		}
	}
	
	public void broadcast(String mensagem) {
		
		for (Cliente cliente : clientes) {
			cliente.enviarMensagem(mensagem);
		}
	}
}
