package com.pw.gamedia.crypto_rates.exchangeRates;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRequest {
  @NotNull
  private String from;
  @NotNull
  @NotEmpty
  private List<String> to;
  @NotNull
  private double amount;
}
