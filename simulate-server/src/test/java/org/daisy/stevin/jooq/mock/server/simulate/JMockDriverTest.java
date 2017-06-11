package org.daisy.stevin.jooq.mock.server.simulate;

import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockDriverTest {
    private final String url = "jdbc:mysql://127.0.0.1:9803/test_group?useLocalSessionState=true";
    private final String user = "root";
    private final String password = "root";

    @BeforeClass
    public void before() {
        try {
            Class.forName("org.daisy.stevin.jooq.mock.server.simulate.JMockDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("can not find driver!");
            e.printStackTrace();
        }
    }

    @AfterClass
    public void after() {
    }

    @BeforeMethod
    public void beforeMethod() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        int rows = stat.executeUpdate("insert into UserTb (id,username)values(1,'Orwell');");
        Assert.assertEquals(rows, 1);
        stat.close();
        conn.close();
    }

    @AfterMethod
    public void afterMethod() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        stat.executeUpdate("delete from UserTb where id=1;");
        stat.close();
        conn.close();
    }

    @Test
    public void selectSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from UserTb where id=1;");
        Assert.assertEquals(rs.next(), true);
        Assert.assertEquals(rs.getInt("id"), 1);
        Assert.assertEquals(rs.getString("username"), "Orwell");
        rs.close();
        stat.close();
        conn.close();
    }

    @Test
    public void insertSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        int row = stat.executeUpdate("insert into user(id,username)values(2,'haha');");
        Assert.assertEquals(row, -1);
        stat.close();
        conn.close();
    }

    @Test
    public void updateSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        int row = stat.executeUpdate("update UserTb set username='hehe' where id=1;");
        Assert.assertEquals(row, 1);
        ResultSet rs = stat.executeQuery("select * from UserTb where id=1;");
        Assert.assertEquals(rs.next(), true);
        Assert.assertEquals(rs.getInt("id"), 1);
        Assert.assertEquals(rs.getString("username"), "hehe");
        rs.close();
        stat.close();
        conn.close();
    }

    @Test
    public void deleteInvalidSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        int row = stat.executeUpdate("delete from UserTb where id=2;");
        Assert.assertEquals(row, 0);
        ResultSet rs = stat.executeQuery("select * from UserTb where id=1;");
        Assert.assertEquals(rs.next(), true);
        Assert.assertEquals(rs.getInt("id"), 1);
        Assert.assertEquals(rs.getString("username"), "Orwell");
        rs.close();
        stat.close();
        conn.close();
    }
    @Test
    public void deleteValidSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        int row = stat.executeUpdate("delete from UserTb where id=1;");
        Assert.assertEquals(row, 1);
        ResultSet rs = stat.executeQuery("select * from UserTb where id=1;");
        Assert.assertEquals(rs.next(), false);
        rs.close();
        stat.close();
        conn.close();
    }
}