package org.daisy.stevin.jooq.mock.server.simple.sql;

import org.daisy.stevin.jooq.mock.server.simple.desc.JMockTables;
import org.daisy.stevin.jooq.mock.server.simple.desc.row.UserRow;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockSelectSql extends JMockSql {
    public JMockSelectSql() {
        super("select");
    }

    @Override
    public MockResult[] execute(String sql, SQLDialect dialect) throws SQLException {
        DSLContext create = DSL.using(dialect);
        Result<UserRow> result = create.newResult(JMockTables.USER_TB);
        result.add(create.newRecord(JMockTables.USER_TB));
        result.get(0).setValue(JMockTables.USER_TB.ID, 1);
        result.get(0).setValue(JMockTables.USER_TB.USERNAME, "Orwell");
        return genMockResult(result.size(),result);
    }
}
