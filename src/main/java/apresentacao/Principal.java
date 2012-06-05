package apresentacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Principal {

	private void exibirMenu() {
		System.out.println("1 - Criar usuário \n");
		System.out.println("2 - Listar usuários \n");
		System.out.println("3 - Adicionar em contatos \n");
		System.out.println("4 - Criar evento \n");
		System.out.println("s - Sair \n");
	}
	
	private void criaUsuario() {
		
	}
	
    private static String readLine(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        return reader.readLine();
    }
	
	public static void main(String args[]) {
		
		Principal main = new Principal();
		main.exibirMenu();
		try {
			String entrada = readLine(System.in);
			
			switch (entrada.charAt(0)) {
				case 1:
					main.criaUsuario();
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
