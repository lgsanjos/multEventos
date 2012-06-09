package negocio;


import java.util.LinkedList;

import persistencia.CheckParamException;
import persistencia.Entidade;
import persistencia.Persistencia;
import persistencia.Tabela;

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
	
	public boolean adicionaContato(String nomeUsuario) {
		return contatos.addContato(nomeUsuario);
	}
	
	public static LinkedList<?> todos() {
		Tabela tabela = Persistencia.getInstancia().procuraTabela(nomeTabela);
		if (tabela == null)
			return new LinkedList<Object>();
		return tabela.todos();
	}
	
	public static Usuario pesquisaNome(String nome) {
		
		for (Object usr : todos()) {
			if ( ((Usuario)usr).getNome().equalsIgnoreCase(nome)) {
				return (Usuario) usr;
			}
		}
		
		return null;
	}
	
	@Override
	protected void testaConsistencia() throws CheckParamException {
	}
	
	public boolean equals(Object usuario) {
		return this.nome.equalsIgnoreCase(((Usuario) usuario).getNome());
	}
	
	public String toString() {
		return this.getNome();
	}
	
}
