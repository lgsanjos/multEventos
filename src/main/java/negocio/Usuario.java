package negocio;


import java.util.LinkedList;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;

public class Usuario extends Entidade {

	private String nome;
	private Contatos contatos;
	public static String nomeTabela = "Usuario";
	
	public Usuario(String nome) {
		super();
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
	
	public String toString() {
		return this.getNome();
	}
	
}
