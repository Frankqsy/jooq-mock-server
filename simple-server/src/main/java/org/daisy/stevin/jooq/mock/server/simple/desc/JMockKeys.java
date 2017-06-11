package org.daisy.stevin.jooq.mock.server.simple.desc;

import org.daisy.stevin.jooq.mock.server.simple.desc.row.UserRow;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;

/**
 * Created by shaoyang.qi on 2017/6/10.
 */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class JMockKeys {
    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<UserRow, Integer> IDENTITY_USER = Identities0.IDENTITY_USER;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<UserRow> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;
    public static final UniqueKey<UserRow> KEY_USER_ID = UniqueKeys0.KEY_USER_ID;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<UserRow, Integer> IDENTITY_USER = createIdentity(JMockTables.USER_TB, JMockTables.USER_TB.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<UserRow> KEY_USER_PRIMARY = createUniqueKey(JMockTables.USER_TB, JMockTables.USER_TB.ID);
        public static final UniqueKey<UserRow> KEY_USER_ID = createUniqueKey(JMockTables.USER_TB, JMockTables.USER_TB.ID);
    }

    private static class ForeignKeys0 extends AbstractKeys {
    }
}
