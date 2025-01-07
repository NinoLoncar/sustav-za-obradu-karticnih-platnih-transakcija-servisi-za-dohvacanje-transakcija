package foi.air.szokpt.transfetch.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tids")
public class Tid {
    @Id
    private String posTid;

    @Column(name = "mcc", nullable = false)
    private String mcc;

    public String getPosTid() {
        return posTid;
    }

    public void setPosTid(String pos_tid) {
        this.posTid = pos_tid;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }
}
