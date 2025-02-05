package com.pw.gamedia.crypto_rates.exchangeRates;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
@AllArgsConstructor
public class ExchangeRateController {

  private final ExecutorService executor = Executors.newFixedThreadPool(4);
  private final ExchangeRateService exchangeRateService;

  @GetMapping("/{currency}")
  public ExchangeRatesResponse getExchangeRates(@PathVariable String currency, @RequestParam(required = false) List<String> filter) {
    return exchangeRateService.getExchangeRates(currency, filter);
  }

  @PostMapping("/exchange")
  public Map<String, Object> performCurrencyExchange(@RequestBody @Valid ExchangeRequest request) throws ExecutionException, InterruptedException {
    CompletableFuture<Map<String, Object>> future = CompletableFuture.supplyAsync(() -> exchangeRateService.performCurrencyExchange(request),
        executor);
    return future.get();
  }
}
