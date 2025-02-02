package foi.air.szokpt.transfetch.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mids")
public class Mid {
    @Id
    @Column(name = "pos_mid")
    @JsonProperty("pos_mid")
    private String posMid;

    @Column(name = "sale_point_name")
    @JsonProperty("sale_point_name")
    private String salePointName;

    @Column(name = "city")
    @JsonProperty("city")
    private String city;

    @Column(name = "state_code")
    @JsonProperty("state_code")
    private String stateCode;

    @Column(name = "type_code")
    @JsonProperty("type_code")
    private String typeCode;

    @Column(name = "location_code")
    @JsonProperty("location_code")
    private String locationCode;

    @Column(name = "postal_code")
    @JsonProperty("postal_code")
    private String postalCode;

    @Column(name = "country_code")
    @JsonProperty("country_code")
    private String countryCode;

    @Column(name = "security_code")
    @JsonProperty("security_code")
    private String securityCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @OneToMany(mappedBy = "mid", fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Tid> tids;

    public Mid() {
    }

    public Mid(String posMid, String salePointName) {
        this.posMid = posMid;
        this.salePointName = salePointName;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
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
