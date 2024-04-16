package com.telran.parking.finecost.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.telran.parking.finecost.dto.FineDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FineDataTopicSender {

	@Autowired
	StreamBridge sb;

	@Bean
	public void sendMessage(FineDto fine) {
		try {
			sb.send("ownerTopicListener-out-0", fine);
		} catch (Exception e) {
			log.error("Error while trying to send the message", e);
		}
	}
}
