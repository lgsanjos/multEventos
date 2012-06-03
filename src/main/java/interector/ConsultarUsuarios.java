package interector;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import negocio.Usuario;
import negocio.Usuarios;
import servidor.Conexao;
import Persistencia.Persistencia;
import Persistencia.Tabela;

public class ConsultarUsuarios {
	
	public Usuarios requisitar() {
		String resposta;
		resposta = Conexao.getInstancia().broadCast(Acoes.consultarNomes);
		
		InputStream inputStream;  
		ObjectInputStream objectInput;  
		Usuarios usuarios = null;  
	      try {  
	         inputStream = new FileInputStream(resposta);  
	         objectInput = new ObjectInputStream(inputStream);  
	         usuarios =  (Usuarios) objectInput.readObject();  
	         return usuarios;  
	           
	      } catch (Exception e) {  
	         e.printStackTrace();
	      }  
	      return null;  		
		
	}
	
	public String responder() {
		Tabela tabela = Persistencia.getInstancia().procuraTabela(Usuario.nomeTabela);
		LinkedList<?> usuarios = tabela.todos();
		
	      FileOutputStream outputStream;
	      ObjectOutputStream objectOutput;  
	      String reposta = new String();
	      
	      for (Object usuario : usuarios) {
		      try {  
		         outputStream = new FileOutputStream("");
		         objectOutput = new ObjectOutputStream( outputStream);  
		         objectOutput.writeObject((Usuario) usuarios);  
		      } catch (IOException e) {  
		         e.printStackTrace();  
		      }
	      }   
	      
		return resposta;
	}	
}
