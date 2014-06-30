package com.xiaobai.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.xiaobai.rabbitmq.Constant;

public class Reveiver {
	
	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.162.129");
			factory.setUsername(Constant.USERNAME1);
			factory.setPassword(Constant.PASSWORD1);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			channel.exchangeDeclare(Constant.EXCHANGE_TOPIC, Constant.TYPE_TOPIC);
			String queueName = channel.queueDeclare().getQueue();
			
			channel.queueBind(queueName, Constant.EXCHANGE_TOPIC, Constant.ROUTINGKEY_TOPIC1);
			channel.queueBind(queueName, Constant.EXCHANGE_TOPIC, Constant.ROUTINGKEY_TOPIC2);
			
			QueueingConsumer consumer = new QueueingConsumer(channel);
	        channel.basicConsume(queueName, true, consumer);

	        while (true) {
	            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	            String message = new String(delivery.getBody());
	            String routingKey = delivery.getEnvelope().getRoutingKey();

	            System.out.println("1 : " + routingKey + "':'" + message + "'");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
