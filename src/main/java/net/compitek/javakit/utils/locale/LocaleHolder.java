package net.compitek.javakit.utils.locale;/**
 * Created by Evgene on 26.07.2015.
 */

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
public class LocaleHolder {
    private static final Logger log = Logger.getLogger(LocaleHolder.class);

    private Locale locale;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
