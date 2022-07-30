package com.ms.email.configs;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.queue}")
	private String queue;

	@Bean
	public org.springframework.amqp.core.Queue queue() {
		return new org.springframework.amqp.core.Queue(queue, true);
	}
	
	/*
	 * Método para conseguir receber corretamente o EmailDto
	 * No método listen
	 */
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
}
