package interector;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import persistencia.Persistencia;
import persistencia.Tabela;

import negocio.Usuario;
import servidor.Conexao;

public class ConsultarUsuarios {
	
	public static LinkedList<String> requisitar() {
		String resposta;
		resposta = Conexao.getInstancia().broadCast(Acoes.CONSULTAR_NOME);
		
		List<String> lista = Arrays.asList(resposta.split("\\s*,\\s*"));
		return new LinkedList<String>(lista);
	}
	
	public static String responder() {
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Usuario.nomeTabela);
		
		String resposta = new String();
		for (Object usuario : tabela.todos()) {
			resposta.concat( ((Usuario) usuario).getNome() + ",");
		}
		
		return resposta;
	}	
}
