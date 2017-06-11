package org.daisy.stevin.jooq.mock.server.simulate.sql;

import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectItem;
import org.daisy.stevin.jooq.mock.server.simulate.db.JDatabase;
import org.daisy.stevin.jooq.mock.server.simulate.db.JRecords;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.tools.jdbc.MockResult;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JMockSelectSql extends JMockSql {
    private final Select statement;
    private final List<String> selectItemStrs = new ArrayList<>();
    private final Map<String, Object> andWhereParams = new HashMap<>();

    public JMockSelectSql(String sql, Statement statement) {
        super(sql, "select");
        this.statement = (Select) statement;
    }

    @Override
    public MockResult[] execute(SQLDialect dialect) throws SQLException {
        parseTableNames(statement);
        parseSelectItems();
        parseWhere();
        JRecords jRecords = JDatabase.getInst().getJRecords(tableName);
        if (jRecords == null) {
            return genMockResult(dialect);
        }
        Result<?> result = jRecords.query(selectItemStrs, andWhereParams, dialect);
        return genMockResult(result == null ? -1 : result.size(), result);
    }

    private void parseSelectItems() {
        SelectBody selectBody = statement.getSelectBody();
        if (!(selectBody instanceof PlainSelect)) {
            selectItemStrs.add("*");
            return;
        }
        PlainSelect plainSelect = (PlainSelect) selectBody;
        List<SelectItem> selectItems = plainSelect.getSelectItems();
        if (selectItems == null || selectItems.isEmpty()) {
            selectItemStrs.add("*");
            return;
        }
        selectItems.forEach(selectItem -> selectItemStrs.add(selectItem.toString()));
    }

    private void parseWhere() {
        SelectBody selectBody = statement.getSelectBody();
        if (!(selectBody instanceof PlainSelect)) {
            return;
        }
        PlainSelect plainSelect = (PlainSelect) selectBody;
        parseWhere(plainSelect.getWhere(), andWhereParams);
    }
}
