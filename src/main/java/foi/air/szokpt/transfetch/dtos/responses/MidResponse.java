package foi.air.szokpt.transfetch.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import foi.air.szokpt.transfetch.entities.Mid;

public class MidResponse {
    @JsonProperty("mid")
    private Mid mid;

    public Mid getMid() {
        return mid;
    }

    public void setMid(Mid mid) {
        this.mid = mid;
    }
}
