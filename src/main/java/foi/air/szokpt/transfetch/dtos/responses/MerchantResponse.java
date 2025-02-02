package foi.air.szokpt.transfetch.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import foi.air.szokpt.transfetch.entities.Merchant;

public class MerchantResponse {
    @JsonProperty("merchant")
    private Merchant merchant;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }
}
