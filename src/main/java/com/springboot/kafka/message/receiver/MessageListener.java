package com.springboot.kafka.message.receiver;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {
	public CountDownLatch latch = new CountDownLatch(3);
	
	@KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
	public void listenGroupFoo(String message) {
		System.out.println("Received Message in group 'foo': " + message);
		latch.countDown();
	}
}
