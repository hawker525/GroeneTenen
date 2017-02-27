package be.vdab.valueobjects;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

import java.io.Serializable;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
public final class Adres implements Serializable{
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(min = 1, max = 50)
    @SafeHtml
    private String straat;

    @NotBlank
    @Length(min = 1, max = 7)
    @SafeHtml
    private String huisNr;

    @NotBlank
    @Length(min = 1, max = 50)
    private Integer postcode;

    @SafeHtml
    private String gemeente;

    public Adres(){}

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
