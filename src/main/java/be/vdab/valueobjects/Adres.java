package be.vdab.valueobjects;

import java.io.Serializable;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
public final class Adres implements Serializable{
    private static final long serialVersionUID = 1L;
    private final String straat;
    private final String huisNr;
    private final Integer postcode;
    private final String gemeente;

    public Adres(String straat, String huisNr,
                 Integer postcode, String gemeente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.postcode = postcode;
        this.gemeente = gemeente;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
