package br.com.caelum.jms;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;

public class TesteConsumidor {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();
		
		//imports do package javax.jms
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection connection = factory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		Destination fila = (Destination) context.lookup("financeiro");
		MessageConsumer consumer = session.createConsumer(fila);
		
		//Tratador de mensagem
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				//Subinterfaces da Message
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("Recebendo msg: " + textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		
		//Message message = consumer.receive(2000);
		
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
