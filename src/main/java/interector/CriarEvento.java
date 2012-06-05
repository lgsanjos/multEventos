package interector;

import java.util.Date;

import persistencia.CheckParamException;

import negocio.Evento;
import negocio.Usuario;
import servidor.Conexao;

public class CriarEvento {

	public static Evento criarEvento(Usuario criador, String titulo, Date dataInicio, Date dataFim) {
		Evento evento = new Evento(criador, titulo, dataInicio, dataFim);
		try {
			evento.salvar();
			return evento;
		} catch (CheckParamException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void adicionarParticipante(Evento evento, String nome) {
		evento.getGrupo().add(nome);
	}
	
	public static void notificarCriacao(Evento evento) {
		
		String par = new String();
		par.concat("Titulo=" + evento.getTitulo() + "&");
		par.concat("Criador=" + evento.getCriador().getNome() + "&");
		par.concat("DataInicio=" + evento.getDataInicio().toString() + "&");
		par.concat("DataFim=" + evento.getDataFim().toString() + "&");
		
		for (String nome : evento.getGrupo()) {
			Conexao.getInstancia().sendTo(nome, Acoes.NOTIFICAR_EVENTO, par);
		}	
	}
	
	public static String confirmarNotificacao(String nomeCriador) {
		return Conexao.getInstancia().sendTo(nomeCriador, Acoes.CONFIRMAR_NOTIFICACAO, "");
	}
		
}
