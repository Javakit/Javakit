package net.compitek.javakit.utils.locale;/**
 * Created by Evgene on 26.07.2015.
 */

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LocaleChangeInterceptorWrapper extends LocaleChangeInterceptor {
    private static final Logger log = Logger.getLogger(LocaleChangeInterceptorWrapper.class);

    @Autowired
    LocaleHolder localeHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        String newLocale = request.getParameter(getParamName());
        if(newLocale != null) {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            if(localeResolver == null) {
                throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
            }

            Locale locale = StringUtils.parseLocaleString(newLocale);
            localeResolver.setLocale(request, response, locale);
            localeHolder.setLocale(locale);
        }

        return true;
    }
}
