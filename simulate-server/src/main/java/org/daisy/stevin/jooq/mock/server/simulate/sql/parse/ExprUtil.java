package org.daisy.stevin.jooq.mock.server.simulate.sql.parse;

import net.sf.jsqlparser.expression.*;

/**
 * Created by shaoyang.qi on 2017/6/11.
 */
public class ExprUtil {
    public static String getVal(Expression expr) {
        if (expr instanceof BinaryExpression) {
            expr = ((BinaryExpression) expr).getRightExpression();
        }
        if (expr instanceof StringValue) {
            return ((StringValue) expr).getValue();
        }
        if (expr instanceof LongValue) {
            return String.valueOf(((LongValue) expr).getStringValue());
        }
        if (expr instanceof DoubleValue) {
            return String.valueOf(((DoubleValue) expr).getValue());
        }
        return expr.toString();
    }
}
