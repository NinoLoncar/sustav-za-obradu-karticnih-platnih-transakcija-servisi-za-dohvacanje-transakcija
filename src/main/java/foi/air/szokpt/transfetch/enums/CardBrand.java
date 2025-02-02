package foi.air.szokpt.transfetch.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum CardBrand {
    VISA,
    MASTERCARD,
    DISCOVER,
    DINERS,
    MAESTRO,
    AMEX,
    UNKNOWN;

    @JsonCreator
    public static CardBrand fromString(String value) {
        try {
            return CardBrand.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return UNKNOWN;
        }
    }
}
