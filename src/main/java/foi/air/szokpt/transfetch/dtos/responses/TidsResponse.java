package foi.air.szokpt.transfetch.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import foi.air.szokpt.transfetch.entities.Tid;

import java.util.List;

public class TidsResponse {
    @JsonProperty("tids")
    private List<Tid> tids;

    public List<Tid> getTids() {
        return tids;
    }

    public void setTids(List<Tid> tids) {
        this.tids = tids;
    }
}
