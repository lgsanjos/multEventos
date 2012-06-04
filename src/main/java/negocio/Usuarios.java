package negocio;

import java.util.LinkedList;

public class Usuarios extends LinkedList<Usuario> {
	
	public String toString() {
		
		String retorno = new String();
		for (Usuario usuario : this) {
			retorno.concat(usuario.getNome() + "\n");
		}
		
		return retorno;
	}

}
