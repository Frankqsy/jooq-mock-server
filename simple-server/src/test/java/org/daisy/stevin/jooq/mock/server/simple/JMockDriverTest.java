package org.daisy.stevin.jooq.mock.server.simple;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
            Class.forName("org.daisy.stevin.jooq.mock.server.simple.JMockDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("can not find driver!");
            e.printStackTrace();
        }
    }

    @AfterClass
    public void after() {
    }

    @Test
    public void selectSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select * from user");
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
        int rows = stat.executeUpdate("insert into user(id,username)values(1,\"haha\");");
        Assert.assertEquals(rows, 1);
        stat.close();
        conn.close();
    }

    @Test
    public void updateSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        int rows = stat.executeUpdate("update user set username=\"hehe\" where id=1;");
        Assert.assertEquals(rows, 1);
        stat.close();
        conn.close();
    }

    @Test
    public void deleteSql() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stat = conn.createStatement();
        int rows = stat.executeUpdate("delete from user where id=1;");
        Assert.assertEquals(rows, 1);
        stat.close();
        conn.close();
    }
}