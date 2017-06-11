package org.daisy.stevin.jooq.mock.server.simulate.db;

import org.daisy.stevin.jooq.mock.server.simulate.desc.tb.UserTb;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public class JDatabase {
    private final Map<String, JRecords> recordsMap = new ConcurrentHashMap<>();
    private static final JDatabase INSTANCE = new JDatabase();

    private JDatabase() {
        recordsMap.put(UserTb.USER_TB.getName(), new JUserRecords());
    }

    public static JDatabase getInst() {
        return INSTANCE;
    }

    public JRecords getJRecords(String tableName) {
        return recordsMap.get(tableName);
    }
}
