package com.pw.gamedia.crypto_rates.integrations.coingecko;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CryptoClientService {

  private final CryptoClient cryptoClient;
  private final CryptoClientConfig cryptoClientConfig;
  private final CryptoClientFilterHandler cryptoClientFilterHandler;

  public Map<String, Map<String, Double>> getExchangeRates(String currency, List<String> filters) {
    String targets = cryptoClientFilterHandler.handleFilter(filters);
    return cryptoClient.getExchangeRates(cryptoClientConfig.getApiKey(), currency, targets);
  }
}
