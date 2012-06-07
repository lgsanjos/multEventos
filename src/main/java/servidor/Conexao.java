package servidor;

import interector.Acoes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Conexao {
	
	private static Conexao instancia;
	private List<String> destinatarios;
	
	public Conexao() {
		destinatarios = new ArrayList<String>();
	}
	
	public static Conexao getInstancia() {
		if (instancia == null)
			instancia = new Conexao();
		
		return instancia;
	}
	
	public String broadCast(Acoes acao) {
		return  broadCast(acao, "");
	}
	
	public String broadCast(Acoes acao, String parametros) {
		
		String retorno = "";
		for (String dest : destinatarios) {
			retorno += sendTo(dest, acao, parametros);
		}
		
		return  retorno;
	}
	
	public void registrarNo(String endereco) {
		this.destinatarios.add(endereco);
	}
	
	public void desregistrarNo(String endereco) {
		this.destinatarios.remove(endereco);
	}
	
	public String sendTo(String destinatario, Acoes acao, String parametros) {
		
		OutputStreamWriter stream = null;
		try {
			String data = URLEncoder.encode("acao=" + acao.toString(),"UTF-8");
			if (! parametros.isEmpty())
				data += URLEncoder.encode( "&" + parametros, "UTF-8");
			
			URL http = new URL(destinatario);
			URLConnection connection = http.openConnection();
			connection.setDoOutput(true);
			
			stream = new OutputStreamWriter(connection.getOutputStream());
			stream.write(data);
			stream.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String retorno = "", linhaLida;
			   
			while ((linhaLida = rd.readLine()) != null) {
				retorno.concat(linhaLida);
			}
		
			rd.close();
			stream.close();
			
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		
	}

}
