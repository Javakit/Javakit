package independent;/**
 * Created by Evgene on 23.07.2015.
 */

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = {"classpath*:test-application-context.xml"})
public class DataBaseTest extends TestCase {
    private static final Logger log = Logger.getLogger(DataBaseTest.class);

    @Autowired
    private DriverManagerDataSource jdbcDataSource;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDatabase() throws Exception {
        Connection connection;
        try {
            connection = jdbcDataSource.getConnection();
        }catch (SQLException e)
        {
            log.error("error during connect to DB",e);
            throw e;
        }
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT DEFAULT_CHARACTER_SET_NAME, DEFAULT_COLLATION_NAME FROM information_schema.SCHEMATA where SCHEMA_NAME like \"javakit\";");
        ResultSet resultSet = preparedStatement.executeQuery();
        assertNotNull(resultSet);
        assertTrue(resultSet.next());
        assertEquals("utf8",resultSet.getString("DEFAULT_CHARACTER_SET_NAME"));
        assertEquals("utf8_general_ci",resultSet.getString("DEFAULT_COLLATION_NAME"));
    }
}
