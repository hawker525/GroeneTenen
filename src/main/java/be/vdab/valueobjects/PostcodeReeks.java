package be.vdab.valueobjects;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * Created by maarten on 24/02/2017.
 */
public class PostcodeReeks {
    @NotNull @Range(min = MIN_POSTCODE, max = MAX_POSTCODE)
    private Integer vanpostcode;
    @NotNull @Range(min = MIN_POSTCODE, max = MAX_POSTCODE)
    private Integer totpostcode;
    private static final int MIN_POSTCODE = 1000;
    private static final int MAX_POSTCODE = 9999;

    public PostcodeReeks() {}

    public boolean bevat(int postcode) {
        return postcode >= vanpostcode && postcode <= totpostcode;
    }

    public Integer getVanpostcode() {
        return vanpostcode;
    }

    public Integer getTotpostcode() {
        return totpostcode;
    }

    public void setVanpostcode(int vanpostcode) {
        this.vanpostcode = vanpostcode;
    }

    public void setTotpostcode(int totpostcode) {
        this.totpostcode = totpostcode;
    }

}
