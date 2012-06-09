package negocio;

import java.util.ArrayList;
import java.util.List;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;
import persistencia.Tabela;

public class NotificacaoDeEvento extends Entidade {

	private Evento evento;
	private boolean isNotificado = false;
	private boolean aceitou = false;
	
	public Evento getEvento() {
		return evento;
	}

	public boolean isNotificado() {
		return isNotificado;
	}
	
	public boolean recusou() {
		return (isNotificado && !aceitou);
	}
	
	public boolean aceitou() {
		return (isNotificado && aceitou);
	}
	
	public void aceitar() {
		aceitou = true;
		isNotificado = true;
	}
	
	public void recusar() {
		aceitou = false;
		isNotificado = true;		
	}
	
	public String getResposta() {
		if (aceitou) return "Aceitou";
		if (recusou()) return "Negou";
		
		return "Não respondeu";
	}
	
	public NotificacaoDeEvento(Evento evento) {
		super();
		this.evento = evento;
	}
	
	public static List<NotificacaoDeEvento> listarTodosRespondidos() {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela("NotificacaoDeEvento");
		List<NotificacaoDeEvento> retorno = new ArrayList<NotificacaoDeEvento>();
		
		if (tabela == null)
			return retorno;
		
		for (Entidade notificacao : tabela) {
			if (((NotificacaoDeEvento) notificacao).isNotificado()) {
				retorno.add((NotificacaoDeEvento) notificacao);
			}
		}
		
		return retorno;
	}
	
	public static List<NotificacaoDeEvento> listarTodosNaoRespondidos() {
		
		Tabela tabela = Persistencia.getInstancia().procuraTabela("NotificacaoDeEvento");
		List<NotificacaoDeEvento> retorno = new ArrayList<NotificacaoDeEvento>();
		
		if (tabela == null)
			return retorno;
		
		for (Entidade notificacao : tabela) {
			if (((NotificacaoDeEvento) notificacao).isNotificado() == false) {
				retorno.add((NotificacaoDeEvento) notificacao);
			}
		}
		
		return retorno;
	}
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
		
	}

}
