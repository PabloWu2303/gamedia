package com.pw.gamedia.crypto_rates.exchangeRates;

import com.pw.gamedia.crypto_rates.integrations.coingecko.CryptoClientService;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExchangeRateCalculator {

  private final CryptoClientService cryptoClientService;
  final static double PROVISION = 0.01;

  Map<String, Object> calculateExchange(ExchangeRequest request) {
    Map<String, Map<String, Double>> rates = cryptoClientService.getExchangeRates(request.getFrom().toLowerCase(), request.getTo());

    double fromAmount = request.getAmount();
    double fee = fromAmount * PROVISION;
    double amountAfterFee = fromAmount - fee;

    Map<String, Object> response = new LinkedHashMap<>();
    response.put("from", request.getFrom());

    request.getTo().forEach(currency -> response.put(currency,
        new ExchangeCurrencyResult(
            rates.get(request.getFrom().toUpperCase()).get(currency.toUpperCase()),
            fromAmount,
            calculateResult(request, currency, amountAfterFee, rates),
            fee
        )
    ));

    return response;
  }

  private double calculateResult(ExchangeRequest request, String currency, double amountAfterFee, Map<String, Map<String, Double>> rates) {
    return amountAfterFee * rates.get(request.getFrom().toUpperCase()).get(currency.toUpperCase());
  }
}
