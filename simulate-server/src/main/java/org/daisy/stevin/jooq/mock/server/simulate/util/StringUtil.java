package org.daisy.stevin.jooq.mock.server.simulate.util;

/**
 * Created by shaoyang.qi on 2017/6/11.
 */
public class StringUtil {
    public static boolean isInteger(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
