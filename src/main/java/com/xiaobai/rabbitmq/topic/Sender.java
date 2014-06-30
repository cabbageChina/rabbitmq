package com.xiaobai.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.xiaobai.rabbitmq.Constant;

public class Sender {
	
	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.162.129");
			factory.setUsername(Constant.USERNAME1);
			factory.setPassword(Constant.PASSWORD1);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			channel.exchangeDeclare(Constant.EXCHANGE_TOPIC, Constant.TYPE_TOPIC);
			
			String message = "topic one ";
			channel.basicPublish(Constant.EXCHANGE_TOPIC, Constant.ROUTINGKEY_TOPIC1, 
					null, message.getBytes());
			
			
			channel.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
