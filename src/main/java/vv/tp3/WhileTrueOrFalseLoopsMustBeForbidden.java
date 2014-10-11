package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTWhileStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class WhileTrueOrFalseLoopsMustBeForbidden extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {

        Node nodeExpr;
        nodeExpr = (Node)node.jjtGetChild(0);
        if (hasTrueOrFalseExpression(nodeExpr)) {
            //ajout de la violation
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

    private boolean hasTrueOrFalseExpression(Node nodeExpr) {

        if (nodeExpr.jjtGetChild().)
        return false;
    }
}
