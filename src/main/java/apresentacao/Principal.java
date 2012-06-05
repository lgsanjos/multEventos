package apresentacao;

import interector.ConsultarUsuarios;
import interector.CriarUsuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import negocio.Usuario;

public class Principal {
	
	private Usuario usuario;

	private void exibirMenu() {
		System.out.println("1 - Criar usuário");
		System.out.println("2 - Listar usuários");
		System.out.println("3 - Adicionar em contatos");
		System.out.println("4 - Criar evento");
		System.out.println("s - Sair");
	}
	
	private void criaUsuario() {
		System.out.println("Digite o nome do usuário:");
		String nome = readLine();
		this.usuario = CriarUsuario.criarUsuario(nome);
		System.out.println("Usuário criado com sucesso!");
	}
	
	private void listarUsuarios() {
		LinkedList<String> nomes = ConsultarUsuarios.requisitar();
		System.out.print(usuario.getNome());
		for (String nome : nomes) {
			System.out.print(", " + nome);
		}
	}
	
    private static String readLine() {
    	InputStream in = System.in;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
			return reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
    }
	
    private static String menuDeOpcoes() {
    	Principal main = new Principal();
    	main.exibirMenu();
    	String entrada = readLine();
    	System.out.println(entrada + " -> " + entrada.charAt(0));
    	switch (new Integer(entrada)) {
    	case 1:
    		main.criaUsuario();
    		break;
    	case 2:
    		main.listarUsuarios();
    		break;			
    	}
    	return entrada;
    }
    
	public static void main(String args[]) {
		String entrada;
		do {
			entrada = menuDeOpcoes();
		} while (! entrada.equalsIgnoreCase("s"));
	}
}
