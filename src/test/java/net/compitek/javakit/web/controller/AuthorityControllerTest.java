package net.compitek.javakit.web.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Evgene on 26.07.2015.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:test-application-context.xml")
@WebAppConfiguration("classpath*:test-dispatcher-servlet.xml")
public class AuthorityControllerTest {


    MockHttpServletRequest request;

    @Before
    public void setUp() throws Exception {
        request = new MockHttpServletRequest();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCheckIsFree() throws Exception {

    }

    @Test
    public void testCreateNewAccount() throws Exception {

    }

    @Test
    public void testSaveUser() throws Exception {

    }

    @Test
    public void testLogin() throws Exception {

    }

    @Test
    public void testError403() throws Exception {

    }
}