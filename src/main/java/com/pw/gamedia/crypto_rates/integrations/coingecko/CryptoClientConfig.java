package com.pw.gamedia.crypto_rates.integrations.coingecko;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "crypto-client")
class CryptoClientConfig {

  private String apiKey;
  private String defaultFilters;
}
