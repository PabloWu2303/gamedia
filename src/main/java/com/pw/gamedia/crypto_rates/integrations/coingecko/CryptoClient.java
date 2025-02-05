package com.pw.gamedia.crypto_rates.integrations.coingecko;

import java.util.Map;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "cryptoClient", url = "https://min-api.cryptocompare.com")
interface CryptoClient {

  @GetMapping("/data/pricemulti?fsyms={currency}&tsyms={targets}")
  Map<String, Map<String, Double>> getExchangeRates(@RequestHeader("authorization") String apiKey, @PathVariable String currency, @PathVariable String targets);
}
