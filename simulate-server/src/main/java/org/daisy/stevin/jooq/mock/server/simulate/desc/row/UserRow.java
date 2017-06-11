package org.daisy.stevin.jooq.mock.server.simulate.desc.row;

import org.daisy.stevin.jooq.mock.server.simulate.desc.tb.UserTb;
import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class UserRow extends UpdatableRecordImpl<UserRow> implements Record2<Integer, String> {
    private static final long serialVersionUID = 4290816898309779225L;

    public UserRow() {
        super(UserTb.USER_TB);
    }

    public UserRow(Integer id, String username) {
        super(UserTb.USER_TB);
        setId(id);
        setUsername(username);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return UserTb.USER_TB.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return UserTb.USER_TB.USERNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getUsername();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, String> value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, String> value2(String value) {
        setUsername(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Record2<Integer, String> values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    public Integer getId() {
        return (Integer) getValue(0);
    }

    public void setId(Integer value) {
        set(0, value);
    }

    public String getUsername() {
        return (String) getValue(1);
    }

    public void setUsername(String value) {
        set(1, value);
    }
}
