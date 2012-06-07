package apresentacao;

import interector.ConsultarUsuarios;
import interector.CriarUsuario;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class MenuSupport {
	
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
    
    private static void exibirMenu() {
		System.out.println("\nMenu de op��es:");
		System.out.println("1 - Criar usu�rio");
		System.out.println("2 - Listar usu�rios");
		System.out.println("3 - Adicionar em contatos");
		System.out.println("4 - Criar evento");
		System.out.println("s - Sair");
	}
	
	private static void criaUsuario() {
		System.out.println("\n*** Criar Usu�rio ***");
		System.out.print("Digite o nome do usu�rio:");
		String nome = readLine();
		CriarUsuario.criarUsuario(nome);
		System.out.println("Usu�rio criado com sucesso!");
	}
	
	private static void listarUsuarios() {
		LinkedList<String> nomes = ConsultarUsuarios.requisitar();
		String primeiroNome = nomes.pop();
		
		System.out.println("\n*** Listar Usuarios ***");
		System.out.print(primeiroNome);
		for (String nome : nomes) {
			System.out.print(", " + nome);
		}
		System.out.println("");
	}
	
	static String enderecoServidor() {
		System.out.print("Informe o endereco do n� servidor: ");
		return MenuSupport.readLine();
	}
	
	static int portaDoServidor() {
   		System.out.print("Informe a porta do servidor: ");
		return new Integer(readLine());
	}
	
	static String escolhaServidorOuCliente() {
		System.out.println("Deseja iniciar um servidor ou cliente (s/c)? ");
    	return MenuSupport.readLine();
    	
	}
	
   public static String menuDeOpcoes() {
    	exibirMenu();
    	System.out.printf("Digite sua opcao: ");
    	String entrada = MenuSupport.readLine();
    	
    	if (entrada.matches("[0-9]*")) {
    		
	    	switch (new Integer(entrada)) {
	    	case 1:
	    		criaUsuario();
	    		break;
	    	case 2:
	    		listarUsuarios();
	    		break;			
	    	}
    	}
    	
    	return entrada;
    }

}
