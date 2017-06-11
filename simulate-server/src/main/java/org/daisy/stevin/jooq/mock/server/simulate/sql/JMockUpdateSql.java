package org.daisy.stevin.jooq.mock.server.simulate.sql;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;
import org.daisy.stevin.jooq.mock.server.simulate.db.JDatabase;
import org.daisy.stevin.jooq.mock.server.simulate.db.JRecords;
import org.daisy.stevin.jooq.mock.server.simulate.sql.parse.ExprUtil;
import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockUpdateSql extends JMockSql {
    private final Update statement;
    private final Map<String, Object> updateParams = new HashMap<>();
    private final Map<String, Object> andWhereParams = new HashMap<>();

    public JMockUpdateSql(String sql, Statement statement) {
        super(sql, "update");
        this.statement = (Update) statement;
    }

    @Override
    public MockResult[] execute(SQLDialect dialect) throws SQLException {
        parseTableNames(statement);
        parseUpdateParams();
        parseWhere(statement.getWhere(), andWhereParams);
        JRecords jRecords = JDatabase.getInst().getJRecords(tableName);
        if (jRecords == null) {
            return genMockResult(-1, null);
        }
        int row = jRecords.update(updateParams, andWhereParams);
        return genMockResult(row, null);
    }

    private void parseUpdateParams() {
        List<Expression> expressionList = statement.getExpressions();
        if (expressionList == null || expressionList.isEmpty()) {
            return;
        }
        List<Column> columns=statement.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            String columnName = columns.get(i).getColumnName().toLowerCase();
            String columnValue = ExprUtil.getVal(expressionList.get(i));
            updateParams.put(columnName, columnValue);
        }
    }
}
