package net.compitek.javakit.utils.locale;/**
 * Created by Evgene on 26.07.2015.
 */

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;


public class MessageSourceWrapper extends ResourceBundleMessageSource {
    private static final Logger log = Logger.getLogger(MessageSourceWrapper.class);

    @Autowired
    LocaleHolder localeHolder;

    public String getMessage(String code){
        return getMessage(code, (Object[])null);
    }


    public final String getMessage(String code, Object[] args){
        return getMessage(code, args,"");
    }

    public final String getMessage(String code, Object[] args, String defaultMessage){

        return getMessage(code,args,defaultMessage,localeHolder.getLocale());

    }



}
