package be.vdab.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

/**
 * Created by Maarten Westelinck on 9/03/2017 for groenetenen.
 */
@XmlAccessorType(XmlAccessType.FIELD)
class Rate {

    @XmlElement(name = "Rate")
    BigDecimal rate;
}
