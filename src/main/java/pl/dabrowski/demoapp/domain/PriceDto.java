package pl.dabrowski.demoapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDto {
    private final float amount;
    private final String currency;

    public PriceDto(@JsonProperty("amount") float amount,@JsonProperty("currency") String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public float getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "PriceDto{" +
                "amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
