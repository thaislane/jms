package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;

public class TesteConsumidor {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();
		
		//imports do package javax.jms
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		
		new Scanner(System.in).nextLine();
		
		connection.close();
		context.close();
	}

}


/**
 * Criaremos uma conex�o. Por�m, da onde vem a nossa ConnectionFactory? O MOM vai te fornecer! 
 * A ideia � que quando o MOM � inicializado, que ele ja disponibilize essa conex�o dentro de um registro. 
 * Com isso, precisamos apenas pegar essa conex�o dentro de um registro, e esse � o JNDI. O nome utilizado no 
 * lookup � apresentado na documenta��o do MOM.
 * 
 */
