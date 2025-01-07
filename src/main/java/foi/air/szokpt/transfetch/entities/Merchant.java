package foi.air.szokpt.transfetch.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "merchants")
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "oib")
    private String oib;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    private List<Mid> mids;

    public Merchant() {}

    public Merchant(String name, String oib) {
        this.name = name;
        this.oib = oib;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public List<Mid> getMids() {
        return mids;
    }

    public void addMid(Mid mid) {
        mids.add(mid);
        mid.setMerchant(this);
    }
}
