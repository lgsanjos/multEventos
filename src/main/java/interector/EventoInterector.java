package interector;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import negocio.Evento;
import negocio.NotificacaoDeEvento;
import negocio.Usuario;
import apresentacao.Principal;

public class EventoInterector {

	public static Evento criarEvento(String titulo, String dataHora, String participantes) {
		String criador = Principal.getUsuario().getNome();
		
		Evento evento = new Evento(criador, titulo, dataHora);
		try {
			
			String[] lista = participantes.split(",");
			for (String participante : lista) {
				evento.insereConvidado(participante);
			}
			
			evento.salvar();
			notificarCriacao(evento);
			return evento;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void adicionarParticipante(Evento evento, String nome) {
		evento.insereConvidado(nome);
	}
	
	private static void notificarCriacao(Evento evento) throws RemoteException {
		Usuario criador = Principal.getUsuario();
		
		List<String> listaDeContatos = criador.getContatos().asList();
		List<String> listaParaNotificar = new ArrayList<String>(evento.getGrupo().keySet()); 
		
		for (String usuario : listaParaNotificar) {
			
			if (! listaDeContatos.contains(usuario)) {
				throw new RemoteException("Usuario " + usuario + " não está na lista de contatos de " + criador.getNome());
			}
			
			Principal.getServidor().notificarCriacaoDeEvento(usuario, evento);
		}
	}

	public static void respostaDaNotificacao(NotificacaoDeEvento notificacao, String responder) {
		String usuario = Principal.getUsuario().getNome();
		
		try {
			
			if (responder.equalsIgnoreCase("s"))
				Principal.getServidor().confirmaConvite(usuario, notificacao);
			else
				Principal.getServidor().recusaConvite(usuario, notificacao);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
