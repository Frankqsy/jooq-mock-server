package org.daisy.stevin.jooq.mock.server.simulate;

import net.sf.jsqlparser.JSQLParserException;
import org.daisy.stevin.jooq.mock.server.simulate.sql.JMockSql;
import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.MockDataProvider;
import org.jooq.tools.jdbc.MockExecuteContext;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockDataProvider implements MockDataProvider {
    @Override
    public MockResult[] execute(MockExecuteContext mockExecuteContext) throws SQLException {
        String sql = mockExecuteContext.sql();
        System.out.println("SQL Query: " + sql);
        try {
            JMockSql mockSql = JMockSql.parse(sql);
            return mockSql.execute(SQLDialect.MYSQL);
        } catch (JSQLParserException e) {
            throw new SQLException(sql + " :can not be parsed!", e);
        }
    }
}
