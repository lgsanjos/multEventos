package negocio;

import java.util.Date;
import java.util.LinkedList;

import Persistencia.CheckParamException;
import Persistencia.Entidade;

public class Evento extends Entidade {
	
	private LinkedList<Usuario> grupo;
	private Usuario criador;
	private Date dataInicio;
	private Date dataFim;
	private String titulo;
	private boolean confirmado;
	private boolean notificado;
	
	public Evento(Usuario criador, String titulo, Date dataInicio, Date dataFim) {
		this.grupo = new LinkedList<Usuario>();
		this.confirmado = false;
		this.notificado = false;
		this.titulo = titulo;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.criador = criador;
	}
	
	public LinkedList<Usuario> getGrupo() {
		return grupo;
	}

	public Usuario getCriador() {
		return criador;
	}

	public void setCriador(Usuario criador) {
		this.criador = criador;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public boolean isNotificado() {
		return notificado;
	}

	public void setNotificado() {
		this.notificado = true;
	}
	
	public void setConfirmado() {
		this.confirmado = true;
	}	
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
		
	}

}
