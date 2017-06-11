package org.daisy.stevin.jooq.mock.server.simple;

import org.daisy.stevin.jooq.mock.server.simple.sql.JMockSql;
import org.daisy.stevin.jooq.mock.server.simple.sql.JSqlTypeEnum;
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
        JMockSql mockSql = JSqlTypeEnum.getType(sql).getHandler();
        return mockSql.execute(sql, SQLDialect.MYSQL);
    }
}
