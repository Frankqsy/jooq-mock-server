package org.daisy.stevin.jooq.mock.server.simulate.sql.parse;

import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.schema.Column;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by shaoyang.qi on 2017/6/11.
 */
public class EqualsToHandler extends ExpressionHandler {
    private final Map<String, Object> equalsToParams;

    public EqualsToHandler() {
        this(new HashMap<>());
    }

    public EqualsToHandler(Map<String, Object> equalsToParams) {
        Objects.requireNonNull(equalsToParams, () -> "equalsToParams must not be null");
        this.equalsToParams = equalsToParams;
    }

    @Override
    protected void handleIN(InExpression inExpr) {

    }

    @Override
    protected void handleEquality(EqualsTo equalsToExpr) {
        if (equalsToExpr == null) {
            return;
        }
        Expression left = equalsToExpr.getLeftExpression();
        if (!(left instanceof Column)) {
            return;
        }
        Expression right = equalsToExpr.getRightExpression();
        if (right == null) {
            return;
        }
        equalsToParams.put(((Column) left).getColumnName().toLowerCase(), ExprUtil.getVal(right));
    }

    public Map<String, Object> getEqualsToParams() {
        return equalsToParams;
    }
}
