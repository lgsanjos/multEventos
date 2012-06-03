package servidor;

import interector.Acoes;

public class Conexao {
	
	private static Conexao instancia;
	
	public static Conexao getInstancia() {
		if (instancia == null)
			instancia = new Conexao();
		
		return instancia;
	}
	
	public String broadCast(Acoes acao) {
		return  "";
	}

}
