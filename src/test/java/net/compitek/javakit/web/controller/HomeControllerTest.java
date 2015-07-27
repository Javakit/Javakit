package net.compitek.javakit.web.controller;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Evgene on 22.07.2015.
 */

@ContextConfiguration( locations = {"classpath*:test-application-context.xml", "classpath*:test-dispatcher-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)

public class HomeControllerTest extends TestCase {

    private static final Logger log = Logger.getLogger(HomeControllerTest.class);

    public Map map;
    private String viewName;
    @Autowired
    HomeController homeController;

    @Before
    public void setUp() throws Exception {
        this.map = new HashMap();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHome() throws Exception {
        viewName = homeController.home(map);
        assertEquals("home",viewName);
    }
}