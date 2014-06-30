package com.xiaobai.rabbitmq.helloWorld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import com.xiaobai.rabbitmq.Constant;

public class Receiver {
	
	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.162.129");
			factory.setUsername(Constant.USERNAME1);
			factory.setPassword(Constant.PASSWORD1);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(Constant.HELLOWORLD_QUEUE_NAME, consumer);
			
			while(true){
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}