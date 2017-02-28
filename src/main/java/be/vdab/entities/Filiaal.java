package be.vdab.entities;

import be.vdab.valueobjects.Adres;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by Maarten Westelinck on 6/02/2017 for groenetenen.
 */
public class Filiaal implements Serializable{
    private static final long serialVersionUID=1L;
    private long id;
    @NotBlank
    @Length(min = 1, max = 50)
    @SafeHtml
    private String naam;
    private boolean hoofdFiliaal;
    @NotNull
    @Min(0)
    @Digits(integer = 10, fraction = 2)
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private BigDecimal waardeGebouw;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    private LocalDate inGebruikName;
    @Valid
    private Adres adres;

    public Filiaal(){}

    public Filiaal(String naam,
                   boolean hoofdFiliaal,
                   BigDecimal waardeGebouw,
                   LocalDate inGebruikName,
                   Adres adres) {
        this.naam = naam;
        this.hoofdFiliaal = hoofdFiliaal;
        this.waardeGebouw = waardeGebouw;
        this.inGebruikName = inGebruikName;
        this.adres = adres;
    }
    // Je maakt getters en setters voor de private variabelen
    public Filiaal(long id, String naam, boolean hoofdFiliaal,
                   BigDecimal waardeGebouw, LocalDate inGebruikName, Adres adres) {
        this(naam, hoofdFiliaal, waardeGebouw, inGebruikName, adres);
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public boolean isHoofdFiliaal() {
        return hoofdFiliaal;
    }

    public void setHoofdFiliaal(boolean hoofdFiliaal) {
        this.hoofdFiliaal = hoofdFiliaal;
    }

    public BigDecimal getWaardeGebouw() {
        return waardeGebouw;
    }

    public void setWaardeGebouw(BigDecimal waardeGebouw) {
        this.waardeGebouw = waardeGebouw;
    }

    public LocalDate getInGebruikName() {
        return inGebruikName;
    }

    public void setInGebruikName(LocalDate inGebruikName) {
        this.inGebruikName = inGebruikName;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
