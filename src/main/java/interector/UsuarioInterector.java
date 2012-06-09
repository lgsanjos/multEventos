package interector;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import negocio.Usuario;
import persistencia.CheckParamException;
import apresentacao.Principal;

public class UsuarioInterector {
	
	public static List<String> clienteSolicitaTodosOsUsuarios() {
		
		try {
			return Principal.getServidor().listarNomesDosUsuarios();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<String>();
	}
	
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
	
	public static void adicionarNaListaDeContatos( String nomeAdicionado) throws Exception {
		
		Usuario dono = Principal.getUsuario();
		
		if (dono == null)
			throw new Exception("Usuário dono da agenda não encontrado.");
		 
		Usuario usrAdicionado = Usuario.pesquisaNome(nomeAdicionado);
		
		if (usrAdicionado == null) {
			
			List<String> usuarios = clienteSolicitaTodosOsUsuarios();
			for (String usuario : usuarios) {
				if (usuario.equalsIgnoreCase(nomeAdicionado)) {
					dono.adicionaContato(usuario);
					return;
				}
			}
		}
		
		throw new Exception("O usuário " + nomeAdicionado + " não foi encontrado.");
	}
}
