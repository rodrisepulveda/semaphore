package com.example.semaphore.config.jms;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.example.semaphore.to.DatoTo;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Listener {

	@JmsListener(destination = "colaBloqueos", concurrency = "1")
	public void receiveObjectMessage(final Message message) throws JMSException {

		try {

			if (message instanceof ObjectMessage) {

				String stringDato = (String) ((ObjectMessage) message).getObject();

				DatoTo datoTo = new ObjectMapper().readValue(stringDato, DatoTo.class);

				System.out.println("Dato 1: " + datoTo.getDato1());
				System.out.println("Dato 2: " + datoTo.getDato2());

			}

		} catch (IOException e) {

		}

	}

}