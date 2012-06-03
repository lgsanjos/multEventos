package testes;

import java.util.LinkedList;

import junit.framework.TestCase;
import negocio.Usuario;
import Persistencia.CheckParamException;
import Persistencia.Entidade;
import Persistencia.Persistencia;
import Persistencia.Tabela;

public class UsuarioTest extends TestCase {
	
	@Override
	protected void setUp() {
		Persistencia.getInstancia().reset();
	}
  
	public void testRegistroClasseUsuario() {
	
		Usuario usuario = new Usuario("nome");
		try {
			usuario.salvar();
			assertEquals(usuario.getNomeTabela(), "Usuario");
			Tabela usuarioTabela = Persistencia.getInstancia().procuraTabela("Usuario");
			assertNotNull(usuarioTabela);
		} catch (CheckParamException e) {
			fail(e.getMessage());
		}
	}
	
	public void testCadastraUsuarioBasico() {
		try {
			Usuario usuario = new Usuario("usuario de teste");
			usuario.salvar();
		} catch (CheckParamException e) {
			fail(e.getMessage());
		}
		
		LinkedList<Entidade> todos = Usuario.todos();
		assertEquals(1, todos.size());
		assertEquals("usuario de teste", ((Usuario) todos.getFirst()).getNome());
	}
	
	public void testConsultarTodosOsUsuarios() {
		try {
		  new Usuario("usuario de teste").salvar();
		  new Usuario("usuario de teste2").salvar();
		  new Usuario("usuario de teste3").salvar();
		} catch (CheckParamException e) {
			fail(e.getMessage());
		}
		
		LinkedList<Entidade> todos = Usuario.todos();
		assertEquals(3, todos.size());
	}
}
