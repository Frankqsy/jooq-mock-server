package org.daisy.stevin.jooq.mock.server.simulate.desc;

import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class JMockSchema extends SchemaImpl {
    private static final long serialVersionUID = 4290816898309779225L;
    public static final JMockSchema MOCK_SCHEMA = new JMockSchema();

    private JMockSchema() {
        super("MOCK_SCHEMA");
    }

    @Override
    public List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(JMockTables.USER_TB);
    }
}