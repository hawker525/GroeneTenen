package be.vdab.valueobjects;

import be.vdab.constraints.Postcode;
import be.vdab.constraints.PostcodeReeksVanKleinerDanOfGelijkAanTot;

import javax.validation.constraints.NotNull;

/**
 * Created by maarten on 24/02/2017.
 */
@PostcodeReeksVanKleinerDanOfGelijkAanTot
public final class PostcodeReeks {
    @NotNull @Postcode
    private Integer vanpostcode;
    @NotNull @Postcode
    private Integer totpostcode;

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


}
