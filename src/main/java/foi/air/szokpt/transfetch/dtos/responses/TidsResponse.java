package foi.air.szokpt.transfetch.dtos.responses;

import foi.air.szokpt.transfetch.entities.Tid;

import java.util.List;

public class TidsResponse {
    private List<Tid> tids;

    public List<Tid> getTids() {
        return tids;
    }

    public void setTids(List<Tid> tids) {
        this.tids = tids;
    }
}
