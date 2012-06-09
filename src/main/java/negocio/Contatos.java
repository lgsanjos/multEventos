package negocio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Contatos {
	
	private LinkedList<String> usuarios;
	
	public Contatos() {
		usuarios = new LinkedList<String>();
	}
	
	public boolean addContato(String nomeUsuario) {
		return usuarios.add(nomeUsuario);
	}
	
	public boolean removeContato(String nomeUsuario) {
		return usuarios.remove(nomeUsuario);
	}
	
	public List<String> asList() {
		return new ArrayList<String>(usuarios);
	}
	
}
