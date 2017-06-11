package org.daisy.stevin.jooq.mock.server.simple.sql;

import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockSql {
    protected final String sqlType;

    public JMockSql() {
        this("unknown");
    }

    protected JMockSql(String sqlType) {
        this.sqlType = sqlType;
    }

    protected MockResult[] genMockResult(int row, Result<?> result) {
        MockResult[] mock = new MockResult[1];
        mock[0] = new MockResult(row, result);
        return mock;
    }

    public MockResult[] execute(String sql, SQLDialect dialect) throws SQLException {
        throw new SQLException("Statement not supported: " + sql);
    }
}
