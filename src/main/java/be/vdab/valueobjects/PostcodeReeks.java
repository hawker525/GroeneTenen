package be.vdab.valueobjects;

/**
 * Created by maarten on 24/02/2017.
 */
public class PostcodeReeks {
    private Integer vanpostcode;
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
        valideer(vanpostcode);
        this.vanpostcode = vanpostcode;
    }

    public void setTotpostcode(int totpostcode) {
        valideer(totpostcode);
        this.totpostcode = totpostcode;
    }

    private static void valideer(int postcode) {
        if(postcode < MIN_POSTCODE || postcode > MAX_POSTCODE) throw new IllegalArgumentException();
    }
}
