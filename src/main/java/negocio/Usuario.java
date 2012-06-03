package negocio;


import java.io.Serializable;
import java.util.LinkedList;

import Persistencia.CheckParamException;
import Persistencia.Entidade;
import Persistencia.Persistencia;

public class Usuario extends Entidade implements Serializable {

	private static final long serialVersionUID = -8048041001844132328L;
	private String nome;
	private Contatos contatos;
	public static String nomeTabela = "Usuario";
	
	public Usuario(String nome) {
		this.nome = nome;
		this.contatos = new Contatos();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public Contatos getContatos() {
		return this.contatos;
	}
	
	public static LinkedList<?> todos() {
		return Persistencia.getInstancia().procuraTabela(nomeTabela).todos();
	}
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
		//TODO: verificar nome duplicado
	}
	
	public boolean equals(Object usuario) {
		return this.nome.equalsIgnoreCase(((Usuario) usuario).getNome());
	}

}