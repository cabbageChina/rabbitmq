package com.xiaobai.rabbitmq.helloWorld;

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
			
			channel.queueDeclare(Constant.HELLOWORLD_QUEUE_NAME, false,false,false,null);
			
			String message = "Hello World!";
			channel.basicPublish("", Constant.HELLOWORLD_QUEUE_NAME, null, message.getBytes());
			
			channel.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
