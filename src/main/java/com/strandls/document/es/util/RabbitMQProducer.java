/**
 * 
 */
package com.strandls.document.es.util;

import java.io.IOException;

import javax.inject.Inject;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

/**
 * 
 * @author vishnu
 *
 */
public class RabbitMQProducer {

	private static final String EXCHANGE_BIODIV = "biodiv";

	@Inject
	private Channel channel;

	public void setMessage(final String routingKey, String message, String updateType) throws Exception {

		try {
		BasicProperties properties = new BasicProperties(null, null, null, 2, 1, null, null, null, null, null,
				updateType, null, null, null);
		channel.basicPublish(EXCHANGE_BIODIV, routingKey, properties, message.getBytes("UTF-8"));
		System.out.println(" [RABBITMQ] Sent Document Id: '" + message + "'");
		}catch(IOException e) {
			System.out.print("==================================");
			System.out.print(e.toString());
			System.out.print("==================================");
		}
	}

}
