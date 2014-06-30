package com.xiaobai.rabbitmq;

public interface Constant {
	
	public final static String HELLOWORLD_QUEUE_NAME = "hello";
	
	public final static String TASK_QUEUE_NAME = "test_queue";
	
	public final static String EXCHANGE_PUB_SUB = "exchange_pub_sub";
	public final static String QUEUE_PUB_SUB = "queue_pub_sub";
	public final static String TYPE_PUB_SUB = "fanout";
	
	public final static String EXCHANGE_ROUTING = "exchange_ROUTING";
	public final static String TYPE_ROUTING= "direct";
	public final static String ROUTINGKEY_ROUTING1 = "routing1";
	public final static String ROUTINGKEY_ROUTING2 = "routing2";
	
	public final static String EXCHANGE_TOPIC = "exchange_TOPIC";
	public final static String TYPE_TOPIC = "topic";
	public final static String ROUTINGKEY_TOPIC1 = "topic 1";
	public final static String ROUTINGKEY_TOPIC2 = "topic 2";
	
	public final static String USERNAME1 = "xiaobai";
	public final static String PASSWORD1 = "xiaobai520";
	
	public final static String USERNAME2 = "xiaobai2";
	public final static String PASSWORD2 = "xiaobai520";
}
