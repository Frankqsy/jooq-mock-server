package org.daisy.stevin.jooq.mock.server.simple;

import org.jooq.tools.jdbc.MockConnection;
import org.jooq.tools.jdbc.MockDataProvider;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockDriver implements Driver {
    private final MockDataProvider provider = new JMockDataProvider();

    static {
        try {
            DriverManager.registerDriver(new JMockDriver());
        } catch (SQLException s) {
            throw (RuntimeException) new RuntimeException("could not register mock driver!").initCause(s);
        }
        System.out.println(JMockDriver.class.getSimpleName() + " initialization done.");
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return new MockConnection(provider);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return true;
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return new DriverPropertyInfo[0];
    }

    @Override
    public int getMajorVersion() {
        return 1;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public boolean jdbcCompliant() {
        return true;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

}
