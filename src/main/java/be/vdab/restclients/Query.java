package be.vdab.restclients;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Maarten Westelinck on 9/03/2017 for groenetenen.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Query {
    Results results;
}
