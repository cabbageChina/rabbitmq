package com.xiaobai.rabbitmq.workQueues;

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

			channel.basicQos(2);

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(Constant.TASK_QUEUE_NAME, false, consumer);

			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());

				System.out.println("Receiver:"+ message + "'");

				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
