package servidor;

import java.net.Socket;

public class Cliente extends Thread {
	
	private Socket conexao;
	private String nome;
	private byte[] bufferSaida = null;
	
	public Cliente(Socket conexao) {
		this.conexao = conexao;
	}
	
	public String getNome() {
		return nome;
	}
	
	private void trataMensagem(String mensagem) {
		
		if (mensagem.length() == 0)
			return;
			
		if (mensagem.startsWith("userName=")) {
			this.nome = mensagem.substring("userName=".length());
			ClientesManager.getInstance().enviarMensagem("Entrei na sala!", nome);
		} else {
			ClientesManager.getInstance().enviarMensagem(mensagem, nome);
		}
		
	}
	
	public void enviarMensagem(String mensagem, String remetente) {
		enviarMensagem(remetente + ": " +  mensagem);
	}
	
	public void enviarMensagem(String mensagem) {
		bufferSaida = mensagem.getBytes();
	}
		
	public void run() {

		try {
			while (true) {
				sleep(1000L);
				
				if (bufferSaida != null) {
					conexao.getOutputStream().write(bufferSaida, 0, bufferSaida.length);
					bufferSaida = null;
				}

				String buffer = "";
				int bufferRecebido = 0;
				
				while (conexao.getInputStream().available() > 0) {
					bufferRecebido = conexao.getInputStream().read();
					buffer += (char) bufferRecebido;
				}				
				
				trataMensagem(buffer);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
