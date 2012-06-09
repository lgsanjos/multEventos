package apresentacao;

import interector.EventoInterector;
import interector.UsuarioInterector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import negocio.Evento;
import negocio.NotificacaoDeEvento;
import negocio.Usuario;

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
		System.out.println("1 - Listar usu�rios");
		System.out.println("2 - Adicionar em contatos");
		System.out.println("3 - Listar contatos");
		System.out.println("4 - Criar evento");
		System.out.println("5 - Listar notifica��es");
		System.out.println("6 - Listar eventos confirmados");
		System.out.println("s - Sair");
	}
	
	public static Usuario criaUsuario() {
		System.out.println("\n*** Criar Usu�rio ***");
		System.out.print("Digite o nome do usu�rio:");
		String nome = readLine();
		Usuario retorno = UsuarioInterector.criarUsuario(nome);
		System.out.println("Usu�rio criado com sucesso!");
		return retorno;
	}
	
	private static void adicionarEmContatos() {
		System.out.println("\n*** Adicionar em contatos ***");
		System.out.println("Digite o nome do usu�rio a ser adicionado:");
		String nomeAdicionado = readLine();
		try {
			UsuarioInterector.adicionarNaListaDeContatos(nomeAdicionado);
			System.out.println("Usu�rio adicionado aos contatos com sucesso!");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		System.out.println("");
	}
	
	private static void criarEvento() {
		System.out.println("\n*** Criar Evento ***");
		
		System.out.print("Digite o t�tulo do evento:");
		String titulo = readLine();
		System.out.print("Digite a data e hora:");
		String data = readLine();
		System.out.print("Digite o nome dos notificados, separados v�rgula:");
		String participantes = readLine();
		
		EventoInterector.criarEvento(titulo, data, participantes);
		System.out.println("Evento criado com sucesso!");
		System.out.println("");
	}
	
	private static void listarUsuarios() {
		System.out.println("\n*** Listar Usu�rios ***");
		
		List<String> nomes = UsuarioInterector.clienteSolicitaTodosOsUsuarios();
		
		if (nomes == null || nomes.isEmpty())
			return;
		
		String primeiroNome = nomes.remove(0);
		
		System.out.print(primeiroNome);
		for (String nome : nomes) {
			System.out.print(", " + nome);
		}
		System.out.println("");
		 
	}
	
	private static void listarContatos() {
		System.out.println("\n*** Listar Contatos ***");
		
		List<String> nomes = Principal.getUsuario().getContatos().asList();
		if (nomes.size() > 0) {
			String primeiroNome = nomes.remove(0);
			System.out.print(primeiroNome);
			
			for (String nome : nomes) {
				System.out.print(", " + nome);
			}
		}
		System.out.println("");
		 
	}
	
	private static void listaEventosNaoLidos() throws Exception {
		System.out.println("\nEventos n�o respondidos:");
		List<NotificacaoDeEvento> lista = NotificacaoDeEvento.listarTodosNaoRespondidos();

		if (lista.size() > 0) {
			int contador = 0;
			for (NotificacaoDeEvento notificacao : lista) {
				contador ++;
				System.out.println(
						contador +
						" - " +	notificacao.getEvento().getTitulo() +
						" Data: " +	notificacao.getEvento().getDataHora() +
						" por: " + notificacao.getEvento().getUsuarioCriador());
			}
		
			System.out.println("Deseja responder alguma notifica��o (s/n)?");
			String responder =  readLine();
			
			if (responder.equalsIgnoreCase("s")) {
				System.out.println("Digite o n�mero da notifica��o:");
				responder = readLine();
				
				NotificacaoDeEvento notificacao = lista.get(new Integer(responder));
				if (notificacao == null)
					throw new Exception("N�o foi encontrado evento referente ao c�digo informado.");
				
				System.out.println("Confirma a participa��o no evento? (s/n)");
				responder = readLine();
				EventoInterector.respostaDaNotificacao(notificacao, responder);	
			}
		} else {
			System.out.println("N�o h� notifica��es pendentes.");
		}
	}
	
	private static void listarNotificacoes() {
		System.out.println("\n*** Listar Notifica��es ***");
		System.out.println("\nEventos respondidos:");
		
		List<NotificacaoDeEvento> lista = NotificacaoDeEvento.listarTodosRespondidos();
		int contador = 0;
		for (NotificacaoDeEvento notificacao : lista) {
			contador ++;
			System.out.println(
					contador +
					" - " +	notificacao.getEvento().getTitulo() +
					" Data: " +	notificacao.getEvento().getDataHora() +
					" por: " + notificacao.getEvento().getUsuarioCriador() +
					" resposta: " + notificacao.getResposta());
		}		

		try {
			listaEventosNaoLidos();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		System.out.println("");
	}
	
	private static void listarEventos() {
		System.out.println("\n*** Listar eventos ***");
		System.out.println("Eventos criados por mim: ");
		List<Evento> eventos = Evento.todosCriadosPor(Principal.getUsuario().getNome());
		for (Evento evento : eventos) {
			System.out.println("Titulo: " + evento.getTitulo() + " Data: " + evento.getDataHora() + " Est� confirmado: " + evento.isConfirmado() );
		}
		
		System.out.println("\nEventos que estou convidado: ");
		eventos = Evento.todosQueConvidam(Principal.getUsuario().getNome());
		for (Evento evento : eventos) {
			System.out.println("Titulo: " + evento.getTitulo() + " Data: " + evento.getDataHora() + " Est� confirmado: " + evento.isConfirmado() );
		}
		
		System.out.println("");
	}
	
	
	static String enderecoServidor() {
		System.out.print("Informe o endereco do n� servidor: ");
		return MenuSupport.readLine();
	}
	
	static String nomeDaInstancia() {
   		System.out.print("Informe o nome da inst�ncia: ");
		return readLine();
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
	    		listarUsuarios();
	    		break;
	    	case 2:
	    		adicionarEmContatos();
	    		break;
	    	case 3:
	    		listarContatos();
	    		break;	
	    	case 4:
	    		criarEvento();
	    		break;
	    	case 5:
	    		listarNotificacoes();
	    		break;	
	    	case 6:
	    		listarEventos();
	    		break;
	    	}
    	}
    	
    	return entrada;
    }

}
