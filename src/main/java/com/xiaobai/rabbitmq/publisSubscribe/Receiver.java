package com.xiaobai.rabbitmq.publisSubscribe;

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

			channel.exchangeDeclare(Constant.EXCHANGE_PUB_SUB,
					Constant.TYPE_PUB_SUB);
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, Constant.EXCHANGE_PUB_SUB, "");

			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(queueName, true, consumer);

			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());

				System.out.println(queueName + " 1 '" + message + "'");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
