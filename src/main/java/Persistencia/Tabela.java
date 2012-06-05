package persistencia;

import java.util.LinkedList;


public class Tabela extends LinkedList<Entidade> {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private int maiorId = 0;
	
	public String getNome() {
		return this.nome;
	}
	
	public Tabela(String nome) {
		this.nome = nome;
	}
	
	public synchronized int proximaId() {
		return (this.maiorId + 1);
	}
	
	public LinkedList<?> todos() {
		LinkedList<Entidade> lista = new LinkedList<Entidade>();
		for (Entidade item : this) {
			lista.add(item);
		}
		
		return lista;
	}
	
	@Override
	public boolean add(Entidade elemento) {
		boolean res = super.add(elemento);
		if (res) {
			if (elemento.id > this.maiorId) {
				this.maiorId = elemento.id;
			}
		}	
		return res;
	}

}
