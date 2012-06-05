package persistencia;


public abstract class Entidade {
	
	protected Tabela tabela = null;
	protected int id;
	protected abstract void testaConsistencia() throws CheckParamException;

	public String getNomeTabela() {
		return this.getClass().getSimpleName();
	}

	public int getId() {
		return this.id;
	}

	public Entidade() {
		this.id = 0; 
		this.tabela = null;
	}
	
	private void criaTabela() {
		if (this.tabela == null) {
			this.tabela = Persistencia.getInstancia().criaNovaTabelaSeNaoExiste(this.getNomeTabela());
		}		
	}

	public void salvar() throws CheckParamException {
		
		this.criaTabela();
		
		if (this.id == 0) {
			this.id = tabela.proximaId();
		}		
		
		synchronized (this.tabela) {
	
			this.testaConsistencia();
	
			Entidade elemento;
			elemento = Persistencia.getInstancia().procuraElementoNaTabela(this.getNomeTabela(), this.id);
			if (elemento != null && this.equals(elemento)) {
				elemento.remover();
			}
	
			tabela.add(this);
		}	
	}

	public void remover() {
		
		this.criaTabela();
		
		synchronized (this.tabela) {	
			if (Persistencia.getInstancia().procuraElementoNaTabela(this.getNomeTabela(), this.id) != null) {
				
				int i = 0;
				while (i < tabela.size()) {
					if (tabela.get(i).getId() == this.id) {
						tabela.remove(i);
					}
					i++;
				}
			}
		}
		
	}
	
}
