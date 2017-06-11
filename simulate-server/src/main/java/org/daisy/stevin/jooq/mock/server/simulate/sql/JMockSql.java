package org.daisy.stevin.jooq.mock.server.simulate.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.daisy.stevin.jooq.mock.server.simulate.sql.parse.EqualsToHandler;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockSql {
    protected final String sqlType;
    protected final String originSql;
    protected final List<String> tableNames = new ArrayList<>();
    protected String tableName = "";

    public JMockSql(String originSql) {
        this(originSql, "unknown");
    }

    protected JMockSql(String originSql, String sqlType) {
        this.originSql = originSql;
        this.sqlType = sqlType;
    }

    protected MockResult[] genMockResult(SQLDialect dialect) {
        DSLContext create = DSL.using(dialect);
        Result<?> result = create.newResult();
        return genMockResult(result.size(), result);
    }

    protected MockResult[] genMockResult(int row, Result<?> result) {
        MockResult[] mock = new MockResult[1];
        mock[0] = new MockResult(row, result);
        return mock;
    }

    protected void parseTableNames(Statement statement) {
        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder
                .getTableList(statement);
        tableNames.addAll(tableList);
        if (this.tableNames.isEmpty()) {
            return;
        }
        this.tableName = this.tableNames.get(0);
    }

    protected void parseWhere(Expression whereExpr, Map<String, Object> andWhereParams) {
        if (whereExpr == null) {
            return;
        }
        EqualsToHandler equalsToHandler = new EqualsToHandler(andWhereParams);
        equalsToHandler.parseWhereExpr(whereExpr);
    }

    public MockResult[] execute(SQLDialect dialect) throws SQLException {
        throw new SQLException("Statement not supported: " + originSql);
    }

    public static JMockSql parse(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);
        if (statement instanceof Select) {
            return new JMockSelectSql(sql, statement);
        }
        if (statement instanceof Insert) {
            return new JMockInsertSql(sql, statement);
        }
        if (statement instanceof Update) {
            return new JMockUpdateSql(sql, statement);
        }
        if (statement instanceof Delete) {
            return new JMockDeleteSql(sql, statement);
        }
        return new JMockSql(sql);
    }
}
