package org.daisy.stevin.jooq.mock.server.simple.desc.tb;

import org.daisy.stevin.jooq.mock.server.simple.desc.JMockSchema;
import org.daisy.stevin.jooq.mock.server.simple.desc.row.UserRow;
import org.daisy.stevin.jooq.mock.server.simple.desc.JMockKeys;
import org.jooq.*;
import org.jooq.impl.TableImpl;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UserTb extends TableImpl<UserRow> {
    private static final long serialVersionUID = 4290816898309779225L;
    /**
     * The column <code>MOCK_SCHEMA.User.ID</code>.
     */
    public final TableField<UserRow, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>MOCK_SCHEMA.User.Username</code>.
     */
    public final TableField<UserRow, String> USERNAME = createField("username", org.jooq.impl.SQLDataType.CHAR.length(50), this, "");
    public static final UserTb USER_TB = new UserTb();

    /**
     * Create a <code>MOCK_SCHEMA.User</code> table reference
     */
    public UserTb() {
        this("UserTb", null);
    }

    /**
     * Create an aliased <code>MOCK_SCHEMA.User</code> table reference
     */
    public UserTb(String alias) {
        this(alias, USER_TB);
    }

    private UserTb(String alias, Table<UserRow> aliased) {
        this(alias, aliased, null);
    }

    private UserTb(String alias, Table<UserRow> aliased, Field<?>[] parameters) {
        super(alias, JMockSchema.MOCK_SCHEMA, aliased, parameters, "");
    }

    @Override
    public Class<? extends UserRow> getRecordType() {
        return UserRow.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserRow, Integer> getIdentity() {
        return JMockKeys.IDENTITY_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserRow> getPrimaryKey() {
        return JMockKeys.KEY_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserRow>> getKeys() {
        return Arrays.<UniqueKey<UserRow>>asList(JMockKeys.KEY_USER_PRIMARY, JMockKeys.KEY_USER_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserTb as(String alias) {
        return new UserTb(alias, this);
    }

    /**
     * Rename this table
     */
    public UserTb rename(String name) {
        return new UserTb(name, null);
    }

}
