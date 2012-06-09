package compartilhado;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import negocio.Evento;
import negocio.NotificacaoDeEvento;

public interface ClientInterectorInterface extends Remote {
	
	public List<String> getListaDeUsuario() throws RemoteException;
	
	public void notificaEvento(Evento evento) throws RemoteException;

	public void confirmaConvite(String usuario, NotificacaoDeEvento notificacao) throws RemoteException;

	public void recusaConvite(String usuario, NotificacaoDeEvento notificacao) throws RemoteException;

}
