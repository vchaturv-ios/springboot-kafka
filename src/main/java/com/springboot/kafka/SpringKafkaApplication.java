package com.springboot.kafka;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.springboot.kafka.message.producer.MessageProducer;
import com.springboot.kafka.message.receiver.MessageListener;


@SpringBootApplication
public class SpringKafkaApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = SpringApplication.run(SpringKafkaApplication.class, args);
		
		MessageProducer producer = context.getBean(MessageProducer.class);
		MessageListener listener = context.getBean(MessageListener.class);
		
		producer.sendMessage("Hello World");
		listener.latch.await(10, TimeUnit.SECONDS);
	}
	
	
	@Bean
	public MessageProducer messageProducer() {
		return new MessageProducer();
	}
	
	@Bean
	public MessageListener messageListener() {
		return new MessageListener();
	}

}
