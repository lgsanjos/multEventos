package persistencia;

import java.util.LinkedList;

public class Persistencia {
	
	private static Persistencia instancia;
	private LinkedList<Tabela> tabelas = new LinkedList<Tabela>(); 	
		
	public static Persistencia getInstancia() {
		if (instancia == null) {
			instancia = new Persistencia();
		}
		
		return instancia;
	}
	
	public void reset() {
		for (int i = 0; i < this.tabelas.size(); i ++) {
			tabelas.get(i).clear();
		}
		
		tabelas.clear();
		tabelas = new LinkedList<Tabela>();
	}
	
	public synchronized Tabela criaNovaTabelaSeNaoExiste(String nome) {
		Tabela procuraTabela;
		procuraTabela = this.procuraTabela(nome);
		
		if (procuraTabela != null) {
			return procuraTabela;
		}

		Tabela novaTabela = new Tabela(nome);
		this.tabelas.add(novaTabela);
		return this.tabelas.getLast();
		
	}
		
	public Tabela procuraTabela(String nome) {
		for (int i = 0; i < this.tabelas.size(); i++) {
			if (this.tabelas.get(i).getNome().equalsIgnoreCase(nome)) {
				return this.tabelas.get(i);
			}
 		}
		return null;
	}	

	public Entidade procuraElementoNaTabela(String nomeTabela, int id) {
		Tabela tabela = this.procuraTabela(nomeTabela);
		
		if (tabela != null) {
			for (int i = 0; i < tabela.size(); i++) {
				if (tabela.get(i).id == id) {
					return tabela.get(i); 
				}
			}
		}
		
		return null;
	}	
		

}
