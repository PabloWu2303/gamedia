package com.pw.gamedia.crypto_rates.exchangeRates;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExchangeRatesResponse {

  private String source;
  private Map<String, Double> rates;

  public static ExchangeRatesResponse from(Map<String, Map<String, Double>> resultMap) {
    List<ExchangeRatesResponse> responseList = resultMap.entrySet().stream().map(entry -> new ExchangeRatesResponse(entry.getKey(), entry.getValue())).toList();
    if (responseList.size() != 1) {
      throw new IllegalStateException();
    }
    return responseList.get(0);
  }
}
