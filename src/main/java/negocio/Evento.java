package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;


public class Evento extends Entidade implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private HashMap<String,Integer> grupo;
	private String usuarioCriador;
	private String dataHora;
	private String titulo;
	private boolean confirmado;
	
	public Evento(String criador, String titulo, String data) {
		super();
		this.grupo = new HashMap<String, Integer>();
		this.confirmado = false;
		this.titulo = titulo;
		this.dataHora = data;
		this.usuarioCriador = criador;
	}
	
	public HashMap<String,Integer> getGrupo() {
		return grupo;
	}
	
	public String getUsuarioCriador() {
		return usuarioCriador;
	}

	public String getDataHora() {
		return dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado() {
		this.confirmado = true;
	}
	
	public void insereConvidado(String nomeUsuario) {
		grupo.put(nomeUsuario, 0);
	}
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
		
	}
	
	public static List<?> todos() {
		return Persistencia.getInstancia().procuraTabela("Evento").todos();
	}
	
	public boolean todosConfirmaram() {
		
		for (Entry<String, Integer> mano : grupo.entrySet()  ) {
			if (mano.getValue() != 1)
				return false;
		}
		
		return true;
	}

	public void confirmaConvidado(String usuario) {
		grupo.put(usuario, 1);
		
		if (todosConfirmaram()) {
			System.out.println("Todos os integrantes do evento " + getTitulo() + " confirmaram presença na data: " + getDataHora());
		}
	}
	
	public void recusaConvidado(String usuario) {
		grupo.put(usuario, 0);
	}

	public static List<Evento> todosCriadosPor(String nome) {
		List<Evento> lista = new ArrayList<Evento>();
		
		for (Object evento : todos()) {
			if (((Evento) evento).getUsuarioCriador().equalsIgnoreCase(nome))
				lista.add( (Evento) evento);
		}
		
		return lista;
	}

	public static List<Evento> todosQueConvidam(String nome) {
		List<Evento> lista = new ArrayList<Evento>();
		
		for (Object evento : todos()) {
			if (((Evento) evento).getGrupo().containsKey(nome))
				lista.add( (Evento) evento);
		}
		
		return lista;
	}

}
