package com.xiaobai.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.xiaobai.rabbitmq.Constant;

public class Receiver2 {

	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.162.129");
			factory.setUsername(Constant.USERNAME2);
			factory.setPassword(Constant.PASSWORD2);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.exchangeDeclare(Constant.EXCHANGE_ROUTING, Constant.TYPE_ROUTING);
			String queueName = channel.queueDeclare().getQueue();
			
			channel.queueBind(queueName, Constant.EXCHANGE_ROUTING, Constant.ROUTINGKEY_ROUTING1);
			channel.queueBind(queueName, Constant.EXCHANGE_ROUTING, Constant.ROUTINGKEY_ROUTING2);
			
			QueueingConsumer consumer = new QueueingConsumer(channel);
	        channel.basicConsume(queueName, true, consumer);

	        while (true) {
	            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	            String message = new String(delivery.getBody());
	            String routingKey = delivery.getEnvelope().getRoutingKey();

	            System.out.println("2 : " + routingKey + "':'" + message + "'");
	        }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
