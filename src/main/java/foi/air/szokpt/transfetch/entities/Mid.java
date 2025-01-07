package foi.air.szokpt.transfetch.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

public class Mid {
    @Id
    @Column(name = "pos_mid")
    private String posMid;

    @Column(name = "sale_point_name")
    private String salePointName;

    @Column(name = "city")
    private String city;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "location_code")
    private String locationCode;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country_code")
    private String countryCode;

    @Column(name = "security_code")
    private String securityCode;
}
