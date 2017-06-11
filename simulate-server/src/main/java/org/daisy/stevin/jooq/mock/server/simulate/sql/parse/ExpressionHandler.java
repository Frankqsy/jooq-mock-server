package org.daisy.stevin.jooq.mock.server.simulate.sql.parse;

import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.InExpression;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by shaoyang.qi on 2017/6/11.
 */
public abstract class ExpressionHandler {
    //WHERE id IN (1,2)
    protected abstract void handleIN(InExpression inExpr);

    //WHERE id=3
    //WHERE id='4'
    protected abstract void handleEquality(EqualsTo equalsToExpr);

    protected void handleExpr(Expression expr) {
    }

    protected void checkAndOrLogic() {
    }

    public final void parseWhereExpr(Expression whereExpr) {
        if (whereExpr == null) {
            return;
        }
        Deque<Expression> deque = new ArrayDeque<>();
        deque.add(whereExpr);
        while (!deque.isEmpty()) {
            Expression sqlExpr = deque.poll();
            handleExpr(sqlExpr);
            if (sqlExpr instanceof InExpression) {
                handleIN((InExpression) sqlExpr);
                continue;
            }
            if (sqlExpr instanceof BinaryExpression) {
                BinaryExpression binaryExpression = (BinaryExpression) sqlExpr;
                if (binaryExpression instanceof EqualsTo) {
                    handleEquality((EqualsTo) binaryExpression);
                }
                deque.add(binaryExpression.getLeftExpression());
                deque.add(binaryExpression.getRightExpression());
                continue;
            }
        }
    }
}
