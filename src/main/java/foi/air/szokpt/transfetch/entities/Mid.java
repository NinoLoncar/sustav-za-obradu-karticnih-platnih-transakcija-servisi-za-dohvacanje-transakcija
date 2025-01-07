package foi.air.szokpt.transfetch.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mids")
public class Mid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pos_mid")
    private String posMid;

    @Column(name = "sale_point_name")
    private String salePointName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToMany(mappedBy = "mid", fetch = FetchType.LAZY)
    private List<Tid> tids;

    public Mid() {
    }

    public Mid(String posMid, String salePointName) {
        this.posMid = posMid;
        this.salePointName = salePointName;
    }

    public Integer getId() {
        return id;
    }

    public String getPosMid() {
        return posMid;
    }

    public void setPosMid(String posMid) {
        this.posMid = posMid;
    }

    public String getSalePointName() {
        return salePointName;
    }

    public void setSalePointName(String salePointName) {
        this.salePointName = salePointName;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<Tid> getTids() {
        return tids;
    }

    public void addTid(Tid tid) {
        tids.add(tid);
        tid.setMid(this);
    }
}
