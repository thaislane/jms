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
 * Criaremos uma conexão. Porém, da onde vem a nossa ConnectionFactory? O MOM vai te fornecer! 
 * A ideia é que quando o MOM é inicializado, que ele ja disponibilize essa conexão dentro de um registro. 
 * Com isso, precisamos apenas pegar essa conexão dentro de um registro, e esse é o JNDI. O nome utilizado no 
 * lookup é apresentado na documentação do MOM.
 * 
 */
