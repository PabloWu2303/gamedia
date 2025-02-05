package com.pw.gamedia.crypto_rates.exchangeRates

import com.pw.gamedia.crypto_rates.integrations.coingecko.CryptoClientService
import spock.lang.Ignore
import spock.lang.Specification
import spock.lang.Subject

class ExchangeRateCalculatorTest extends Specification {

    CryptoClientService cryptoClientService = Mock()
    @Subject ExchangeRateCalculator calculator = new ExchangeRateCalculator(cryptoClientService)

    def setup(){
        cryptoClientService.getExchangeRates(_, _) >> [
                "CURRENCYA": [
                        "CURRENCYB": 1 as double,
                        "CURRENCYC": 2 as double
                ]
        ]
    }

    @Ignore
    def "Should return calculated result" () {
        given:
        def request = new ExchangeRequest(from: "currencyA", to: ["currencyB", "currencyC"], amount: 10)

        when:
        def result = calculator.calculateExchange(request)

        then:
        result == [
                "from": "currencyA",
                "currencyB": [
                        rate: 1.0,
                        amount: 10.0,
                        result: 9.9,
                        fee: 0.1
                ],
                "currencyC": [
                        rate: 2.0,
                        amount: 10.0,
                        result: 19.8,
                        fee: 0.1
                ]
        ]
    }
}
