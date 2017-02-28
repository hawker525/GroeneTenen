package be.vdab.web;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

/**
 * Created by Maarten Westelinck on 28/02/2017 for groenetenen.
 */
@Component
@SessionScope
public class VoorkeurenImpl implements Voorkeur, Serializable{
    private static final long serialVersionUID = 1L;
    private String foto;


    @Override
    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String getFoto() {
        return foto;
    }
}
