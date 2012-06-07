package interector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import negocio.Usuario;
import persistencia.Persistencia;
import persistencia.Tabela;
import servidor.ClientesManager;

public class ConsultarUsuarios {
	
	public static LinkedList<String> requisitar() {
		String resposta;
		resposta = ""; ClientesManager.getInstance().broadcast(Acoes.CONSULTAR_NOME.toString());
		
		List<String> lista = new ArrayList<String>();
		if (! resposta.isEmpty())
			lista.addAll(Arrays.asList(resposta.split("\\s*,\\s*")));
		
		for (Object usuario : Usuario.todos())
			lista.add( ((Usuario) usuario).getNome());
		
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
