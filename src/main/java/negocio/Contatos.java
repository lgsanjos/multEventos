package negocio;

import persistencia.CheckParamException;
import persistencia.Entidade;

public class Contatos extends Entidade {
	
	private Usuarios usuarios;
	
	public Contatos() {
		usuarios = new Usuarios();
	}
	
	public boolean addContato(Usuario usuario) {
		return usuarios.add(usuario);
	}
	
	public boolean removeContato(Usuario usuario) {
		return usuarios.remove(usuario);
	}
	
	@Override
	protected void testaConsistencia() throws CheckParamException {

	}

}
