package compartilhado;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import negocio.Evento;
import negocio.NotificacaoDeEvento;
import negocio.Usuario;
import persistencia.CheckParamException;

public class ClientInterector extends UnicastRemoteObject implements ClientInterectorInterface {

	private static final long serialVersionUID = -978001454493123132L;

	public ClientInterector() throws RemoteException {
		super();
	}

	@Override
	public List<String> getListaDeUsuario() throws RemoteException {
		LinkedList<?> usuarios = Usuario.todos();
		
		ArrayList<String> retorno = new ArrayList<String>();
		for (Object usuario : usuarios) {
			retorno.add(((Usuario) usuario).getNome());
		}
		
		return retorno;
	}
	
	public void notificaEvento(Evento evento) {
		
		NotificacaoDeEvento notificacao = new NotificacaoDeEvento(evento);
		try {
			notificacao.salvar();
		} catch (CheckParamException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void confirmaConvite(String usuario, NotificacaoDeEvento notificacao) throws RemoteException {
		
		List<?> eventos = Evento.todos();
		for (Object evento : eventos) {
			Evento castedEvento = (Evento) evento;
			
			if (castedEvento.getTitulo().equalsIgnoreCase(notificacao.getEvento().getTitulo())) {
				castedEvento.confirmaConvidado(usuario);
				return;
			}
		}
	}

	@Override
	public void recusaConvite(String usuario, NotificacaoDeEvento notificacao) throws RemoteException {
		List<?> eventos = Evento.todos();
		for (Object evento : eventos) {
			Evento castedEvento = (Evento) evento;
			
			if (castedEvento.getTitulo().equalsIgnoreCase(notificacao.getEvento().getTitulo())) {
				castedEvento.recusaConvidado(usuario);
				return;
			}
		}
		
	}

}
