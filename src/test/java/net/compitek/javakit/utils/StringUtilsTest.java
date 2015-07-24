package net.compitek.javakit.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import net.compitek.javakit.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Evgene on 23.07.2015.
 */

public class StringUtilsTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGeneratePassword() throws Exception {
        assertTrue(StringUtils.generatePassword(-4).length()==5);
        assertTrue(StringUtils.generatePassword(0).length()==5);
        assertTrue(StringUtils.generatePassword(1).length() == 5);
        assertTrue(StringUtils.generatePassword(5).length() == 5);
        assertTrue(StringUtils.generatePassword(6).length()==6);
        assertTrue(StringUtils.generatePassword(10).length()==10);
        assertTrue(StringUtils.generatePassword(12).length()==12);
        assertTrue(StringUtils.generatePassword(15).length()==12);
    }

    @Test
    public void testCreateIdsString() throws Exception {
        assertEquals("(-1)",StringUtils.createIdsString(null));

        List<Long> IdsList=new ArrayList<Long>();

        IdsList.add(3L);
        assertEquals("(3,-1)",StringUtils.createIdsString(IdsList));

        IdsList.add(278L);
        assertEquals("(3,278,-1)",StringUtils.createIdsString(IdsList));

        IdsList.add(5438723323415675L);
        assertEquals("(3,278,5438723323415675,-1)",StringUtils.createIdsString(IdsList));

        IdsList.add(0L);
        assertEquals("(3,278,5438723323415675,0,-1)",StringUtils.createIdsString(IdsList));

        IdsList.add(-1L);
        assertEquals("(3,278,5438723323415675,0,-1,-1)",StringUtils.createIdsString(IdsList));


        IdsList.add(-5438723323415675L);
        assertEquals("(3,278,5438723323415675,0,-1,-5438723323415675,-1)",StringUtils.createIdsString(IdsList));

        IdsList.add(3L);
        assertEquals("(3,278,5438723323415675,0,-1,-5438723323415675,3,-1)",StringUtils.createIdsString(IdsList));
    }
}