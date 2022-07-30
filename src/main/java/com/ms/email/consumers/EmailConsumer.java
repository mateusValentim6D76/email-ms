package com.ms.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;

@Component
public class EmailConsumer {

	@Autowired
	EmailService emailService;

	/*
	 * RabbitListener mostra que esse método será um ouvinte da fila setada em
	 * ${spring.rabbitmq.queue}
	 */
	@RabbitListener(queues = "${spring.rabbitmq.queue}")
	public void listen(@Payload EmailDto emailDto) {
		EmailModel emailModel = new EmailModel();
		// Convertendo EmailDto em EmailModel para persistir no banco
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
		System.out.println("Email Status: " + emailModel.getStatusEmail().toString());
	}
}
