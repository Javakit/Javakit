package net.compitek.javakit.utils;/**
 * Created by Evgene on 17.07.2015.
 */

import org.apache.log4j.Logger;

import java.util.List;

public class StringUtils {
    private static final Logger log = Logger.getLogger(StringUtils.class);

    public static String generatePassword(int length){
        if (length<5)length=5;
        else if(length>12) length=12;
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789<>?/;:/*-+.#$%^&£!";
        int i = 0;
        int maxNumber = alphabet.length();
        StringBuilder response=new StringBuilder();
        while(i < length){
            int rand = (int) (Math.random() * maxNumber);
            response.append(alphabet.charAt(rand));
            i++;
        }
        return response.toString();
    }


    public static String createIdsString(List<Long> ids) {
        if(ids == null) return "(-1)";
        StringBuilder builder = new StringBuilder();
        builder.append("(");
        for (Long id : ids) {
            builder.append(id.toString() + ",");
        }
        builder.append("-1");
        builder.append(")");
        return builder.toString();
    }
}
