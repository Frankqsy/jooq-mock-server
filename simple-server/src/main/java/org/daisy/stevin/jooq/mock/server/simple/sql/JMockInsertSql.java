package org.daisy.stevin.jooq.mock.server.simple.sql;

import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockInsertSql extends JMockSql{

    public JMockInsertSql() {
        super("insert");
    }

    @Override
    public MockResult[] execute(String sql, SQLDialect dialect) throws SQLException {
        return genMockResult(1,null);
    }
}
