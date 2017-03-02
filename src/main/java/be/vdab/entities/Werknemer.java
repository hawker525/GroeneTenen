package be.vdab.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Maarten Westelinck on 2/03/2017 for groenetenen.
 */
@Entity
@Table(name = "werknemers")
public class Werknemer implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String voornaam;
    private String familienaam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "filiaalId")
    private Filiaal filiaal;
    private BigDecimal wedde;
    private long rijksregisterNr;

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public void setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
    }

    public Filiaal getFiliaal() {
        return filiaal;
    }

    public void setFiliaal(Filiaal filiaal) {
        this.filiaal = filiaal;
    }

    public BigDecimal getWedde() {
        return wedde;
    }

    public void setWedde(BigDecimal wedde) {
        this.wedde = wedde;
    }

    public long getRijksregisterNr() {
        return rijksregisterNr;
    }

    public void setRijksregisterNr(long rijksregisterNr) {
        this.rijksregisterNr = rijksregisterNr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Werknemer werknemer = (Werknemer) o;

        return getRijksregisterNr() == werknemer.getRijksregisterNr();
    }

    @Override
    public int hashCode() {
        return (int) (getRijksregisterNr() ^ (getRijksregisterNr() >>> 32));
    }
}
