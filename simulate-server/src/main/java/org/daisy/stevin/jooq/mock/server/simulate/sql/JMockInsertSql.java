package org.daisy.stevin.jooq.mock.server.simulate.sql;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.insert.Insert;
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
public class JMockInsertSql extends JMockSql {
    private final Insert statement;
    private final Map<String, Object> valuesParams = new HashMap<>();

    public JMockInsertSql(String sql, Statement statement) {
        super(sql, "insert");
        this.statement = (Insert) statement;
    }

    @Override
    public MockResult[] execute(SQLDialect dialect) throws SQLException {
        parseTableNames(statement);
        parseValueParams();
        JRecords jRecords = JDatabase.getInst().getJRecords(tableName);
        if (jRecords == null) {
            return genMockResult(-1, null);
        }
        int row = jRecords.insert(valuesParams);
        return genMockResult(row, null);
    }

    private void parseValueParams() {
        ExpressionList expressionList = (ExpressionList) statement.getItemsList();
        if (expressionList == null) {
            return;
        }
        List<Expression> expressions = expressionList.getExpressions();
        if (expressions == null || expressions.isEmpty()) {
            return;
        }
        List<Column> columns = statement.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            String columnName = columns.get(i).getColumnName().toLowerCase();
            String columnValue = ExprUtil.getVal(expressions.get(i));
            valuesParams.put(columnName, columnValue);
        }
    }
}
