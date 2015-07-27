package net.compitek.javakit.utils;/**
 * Created by Evgene on 27.07.2015.
 */

import net.compitek.javakit.database.domain.PersistenceEntity;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersistanceEntityUtils {
    private static final Logger log = Logger.getLogger(PersistanceEntityUtils.class);

    public static List<Long> toListLong(List<? extends PersistenceEntity> entityList){
        List<Long> response = new ArrayList<Long>();
        for (PersistenceEntity entity : entityList) {
            response.add(entity.getId());
        }
        return  response;
    }
}
