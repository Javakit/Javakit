package net.compitek.javakit.web.controller;

import junit.framework.TestCase;
import net.compitek.javakit.database.dao.NewsDao;
import net.compitek.javakit.service.NewsService;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextLoader;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Evgene on 22.07.2015.
 */

@ContextConfiguration( locations = {"classpath*:application-context.xml","classpath*:dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class SimpleControllerTest extends TestCase {

    private static final Logger log = Logger.getLogger(SimpleControllerTest.class);

    public Map map;
    private String viewName;
    @Autowired
    SimpleController simpleController;

    @Before
    public void setUp() throws Exception {
        this.map = new HashMap();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHome() throws Exception {
        viewName = simpleController.home(map);
        assertEquals("home",viewName);
    }
}