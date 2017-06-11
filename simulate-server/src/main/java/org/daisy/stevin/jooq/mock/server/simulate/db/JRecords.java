package org.daisy.stevin.jooq.mock.server.simulate.db;

import org.jooq.Result;
import org.jooq.SQLDialect;

import java.util.List;
import java.util.Map;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public interface JRecords {
    public Result<?> query(List<String> selectItemStrs, Map<String, Object> andWhereParams, SQLDialect dialect);

    public int insert(Map<String, Object> valuesParams);

    public int update(Map<String, Object> updateParams, Map<String, Object> andWhereParams);

    public int delete(Map<String, Object> andWhereParams);
}
