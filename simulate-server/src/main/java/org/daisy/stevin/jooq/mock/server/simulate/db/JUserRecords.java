package org.daisy.stevin.jooq.mock.server.simulate.db;

import org.daisy.stevin.jooq.mock.server.simulate.desc.JMockTables;
import org.daisy.stevin.jooq.mock.server.simulate.desc.row.UserRow;
import org.daisy.stevin.jooq.mock.server.simulate.util.StringUtil;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JUserRecords implements JRecords {
    private Map<Integer, UserRow> records = new HashMap<>();

    @Override
    public Result<UserRow> query(List<String> selectItemStrs, Map<String, Object> andWhereParams, SQLDialect dialect) {
        DSLContext create = DSL.using(dialect);
        Result<UserRow> result = create.newResult(JMockTables.USER_TB);
        String idStr = (String) andWhereParams.get(JMockTables.USER_TB.ID.getName());
        if (!StringUtil.isInteger(idStr)) {
            return null;
        }
        UserRow row = records.get(Integer.valueOf(idStr));
        if (row != null) {
            UserRow newRow=row.copy();
            newRow.setId(row.getId());
            result.add(newRow);
        }
        return result;
    }

    @Override
    public int insert(Map<String, Object> valuesParams) {
        UserRow row = new UserRow();
        String idStr = (String) valuesParams.get(JMockTables.USER_TB.ID.getName());
        if (!StringUtil.isInteger(idStr)) {
            return -1;
        }
        int id = Integer.valueOf(idStr);
        if (records.containsKey(id)) {
            return -1;
        }
        row.setId(id);
        String username = (String) valuesParams.get(JMockTables.USER_TB.USERNAME.getName());
        row.setUsername(username);
        records.put(row.getId(), row);
        return 1;
    }

    @Override
    public int update(Map<String, Object> updateParams, Map<String, Object> andWhereParams) {
        String idStr = (String) andWhereParams.get(JMockTables.USER_TB.ID.getName());
        if (!StringUtil.isInteger(idStr)) {
            return -1;
        }
        int id = Integer.valueOf(idStr);
        if (!records.containsKey(id)) {
            return 0;
        }
        UserRow row = records.get(id);
        String username = (String) updateParams.get(JMockTables.USER_TB.USERNAME.getName());
        row.setUsername(username);
        return 1;
    }

    @Override
    public int delete(Map<String, Object> andWhereParams) {
        String idStr = (String) andWhereParams.get(JMockTables.USER_TB.ID.getName());
        if (!StringUtil.isInteger(idStr)) {
            return -1;
        }
        int id = Integer.valueOf(idStr);
        if (!records.containsKey(id)) {
            return 0;
        }
        records.remove(id);
        return 1;
    }
}
