package be.vdab.restservices;

import be.vdab.entities.Filiaal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Maarten Westelinck on 9/03/2017 for groenetenen.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class FiliaalIdNaam {
    @XmlAttribute
    private long id;
    @XmlAttribute
    private String naam;

    public FiliaalIdNaam() {}

    public FiliaalIdNaam(Filiaal filiaal) {
        this.id = filiaal.getId();
        this.naam = filiaal.getNaam();
    }
}
