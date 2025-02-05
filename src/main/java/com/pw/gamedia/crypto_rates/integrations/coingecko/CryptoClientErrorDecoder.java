package com.pw.gamedia.crypto_rates.integrations.coingecko;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CryptoClientErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    return switch (response.status()) {
      case 400 -> new FeignException.BadRequest(response.reason(), response.request(), response.request().body(), response.headers());
      case 401 -> new FeignException.Unauthorized(response.reason(), response.request(), response.request().body(), response.headers());
      case 403 -> new FeignException.Forbidden(response.reason(), response.request(), response.request().body(), response.headers());
      case 404 -> new FeignException.NotFound(response.reason(), response.request(), response.request().body(), response.headers());
      case 500 -> new FeignException.InternalServerError(response.reason(), response.request(), response.request().body(), response.headers());
      default -> new FeignException.FeignServerException(response.status(), response.reason(), response.request(), response.request().body(), response.headers());
    };
  }
}
