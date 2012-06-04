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
	
	public String broadCast(Acoes acao, String parametros) {
		return  "";
	}
	
	public String sendTo(String destinatario, Acoes acao, String parametros) {
		return "";
	}

}
