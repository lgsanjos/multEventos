package interector;

import persistencia.CheckParamException;
import negocio.Usuario;

public class CriarUsuario {
	
	public static Usuario criarUsuario(String nome) {
		
		Usuario usuario = new Usuario(nome);
		try {
			usuario.salvar();
			return usuario;
		} catch (CheckParamException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
