package com.xiaobai.rabbitmq.workQueues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
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
			
			channel.queueDeclare(Constant.TASK_QUEUE_NAME, true, false, false, null);
			
			String message = "task work queues";
			channel.basicPublish("", Constant.TASK_QUEUE_NAME, 
					MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			
			channel.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
