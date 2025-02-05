package com.pw.gamedia.crypto_rates.integrations.coingecko;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CryptoClientFilterHandler {

  private final CryptoClientConfig cryptoClientConfig;

  public String handleFilter(List<String> filters) {
    return filters != null ? String.join(",", filters).toLowerCase() : cryptoClientConfig.getDefaultFilters();
  }
}
