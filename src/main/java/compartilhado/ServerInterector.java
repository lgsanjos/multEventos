package compartilhado;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import negocio.Evento;
import negocio.NotificacaoDeEvento;

public class ServerInterector extends UnicastRemoteObject implements ServerInterectorInterface {

	private static final long serialVersionUID = 2718651236855852435L;
	private HashMap<String,ClientInterectorInterface> listaDeClientes;
	
	public ServerInterector() throws RemoteException {
		super();
		listaDeClientes = new HashMap<String, ClientInterectorInterface>();
	}
	
	@Override
	public void registerClientInterector(String nome, String uri) throws RemoteException {
		
		ClientInterectorInterface cliente;
		try {
			cliente = (ClientInterectorInterface) Naming.lookup(uri);
			listaDeClientes.put(nome, cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> listarNomesDosUsuarios() throws RemoteException {
		
		ArrayList<String> retorno = new ArrayList<String>();
		for (ClientInterectorInterface cliente : listaDeClientes.values()) {
			retorno.addAll(cliente.getListaDeUsuario());
		}
		return retorno;
	}
	
	public void notificarCriacaoDeEvento(String usuario, Evento evento) throws RemoteException {
		ClientInterectorInterface cliente = listaDeClientes.get(usuario);
		
		if (cliente != null) {
			cliente.notificaEvento(evento);
		}
	}
	
	public void confirmaConvite(String usuario, NotificacaoDeEvento notificacao) throws RemoteException {
		String criador = notificacao.getEvento().getUsuarioCriador();
		listaDeClientes.get(criador).confirmaConvite(usuario, notificacao);
	}

	@Override
	public void recusaConvite(String usuario, NotificacaoDeEvento notificacao)	throws RemoteException {
		String criador = notificacao.getEvento().getUsuarioCriador();
		listaDeClientes.get(criador).recusaConvite(usuario, notificacao);		
	}

}
