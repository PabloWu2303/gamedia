package com.pw.gamedia.crypto_rates.exchangeRates;

import com.pw.gamedia.crypto_rates.integrations.coingecko.CryptoClientService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExchangeRateService {

  private final CryptoClientService cryptoClientService;
  private final ExchangeRateCalculator calculator;

  ExchangeRatesResponse getExchangeRates(String currency, List<String> filters) {
    return ExchangeRatesResponse.from(cryptoClientService.getExchangeRates(currency.toLowerCase(), filters));
  }

  public Map<String, Object> performCurrencyExchange(@Valid ExchangeRequest request) {
    return calculator.calculateExchange(request);
  }
}
