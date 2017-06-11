package org.daisy.stevin.jooq.mock.server.simulate.sql;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import org.daisy.stevin.jooq.mock.server.simulate.db.JDatabase;
import org.daisy.stevin.jooq.mock.server.simulate.db.JRecords;
import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockDeleteSql extends JMockSql {
    private final Delete statement;
    private final Map<String, Object> andWhereParams = new HashMap<>();

    public JMockDeleteSql(String sql, Statement statement) {
        super(sql, "delete");
        this.statement = (Delete) statement;
    }

    @Override
    public MockResult[] execute(SQLDialect dialect) throws SQLException {
        parseTableNames(statement);
        parseWhere(statement.getWhere(), andWhereParams);
        JRecords jRecords = JDatabase.getInst().getJRecords(tableName);
        if (jRecords == null) {
            return genMockResult(-1, null);
        }
        int row = jRecords.delete(andWhereParams);
        return genMockResult(row, null);
    }
}
