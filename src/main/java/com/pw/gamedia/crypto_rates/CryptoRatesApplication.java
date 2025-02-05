package com.pw.gamedia.crypto_rates;

import com.pw.gamedia.crypto_rates.integrations.coingecko.CryptoClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class CryptoRatesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoRatesApplication.class, args);
	}

	@Bean
	public ErrorDecoder errorDecoder() {
		return new CryptoClientErrorDecoder();
	}
}
