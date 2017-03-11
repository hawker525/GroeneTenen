package be.vdab.mail;

import be.vdab.entities.Filiaal;

/**
 * Created by maarten on 11/03/2017.
 */
public interface MailSender {
    void nieuwFiliaalMail(Filiaal filiaal);
}
