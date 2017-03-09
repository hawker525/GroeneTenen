package be.vdab.restclients;

import be.vdab.exception.KanKoersNietLezenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Maarten Westelinck on 9/03/2017 for groenetenen.
 */
@Component
public class YahooKoersenClient implements KoersenClient{
    private static final Logger LOGGER = Logger.getLogger(YahooKoersenClient.class.getName());
    private final URI yahooURl;
    private final RestTemplate restTemplate;

    public YahooKoersenClient(@Value("${yahooKoersenURL}") URI yahooUrl, RestTemplate restTemplate) {
        this.yahooURl = yahooUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getDollarKoers() {
        try {
            Query query = restTemplate.getForObject(yahooURl, Query.class);
            return query.results.rate.rate;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "kan koers niet lezen", ex);
            throw new KanKoersNietLezenException();
        }
    }
}
