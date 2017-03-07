package be.vdab.restservices;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

/**
 * Created by Maarten Westelinck on 7/03/2017 for groenetenen.
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
