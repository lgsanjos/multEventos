package compartilhado;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import negocio.Evento;
import negocio.NotificacaoDeEvento;

public interface ServerInterectorInterface extends Remote {

	public void registerClientInterector(String nome, String uri)	throws RemoteException;
	
	public List<String> listarNomesDosUsuarios() throws RemoteException;
	
	public void notificarCriacaoDeEvento(String usuario, Evento evento) throws RemoteException;

	public void confirmaConvite(String usuario, NotificacaoDeEvento notificacao)  throws RemoteException;

	public void recusaConvite(String usuario, NotificacaoDeEvento notificacao) throws RemoteException ;

}
