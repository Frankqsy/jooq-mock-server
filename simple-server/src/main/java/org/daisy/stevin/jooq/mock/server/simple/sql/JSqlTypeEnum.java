package org.daisy.stevin.jooq.mock.server.simple.sql;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
public enum JSqlTypeEnum {
    /** unknown */
    UNKNOWN("unknown", new JMockSql()),
    /** select */
    SELECT("select", new JMockSelectSql()),
    /** insert */
    INSERT("insert", new JMockInsertSql()),
    /** update */
    UPDATE("update", new JMockUpdateSql()),
    /** delete */
    DELETE("delete", new JMockDeleteSql());
    private final String type;
    private final JMockSql handler;

    private JSqlTypeEnum(String type, JMockSql handler) {
        this.type = type;
        this.handler = handler;
    }

    public static JSqlTypeEnum getType(String sql) {
        if (sql == null || sql.trim().length() <= 0) {
            return UNKNOWN;
        }
        sql = sql.toLowerCase();
        for (JSqlTypeEnum sqlType : values()) {
            if (sql.startsWith(sqlType.type)) {
                return sqlType;
            }
        }
        return UNKNOWN;
    }

    public JMockSql getHandler() {
        return handler;
    }
}

