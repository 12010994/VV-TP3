package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTBooleanLiteral;
import net.sourceforge.pmd.lang.java.ast.ASTBreakStatement;
import net.sourceforge.pmd.lang.java.ast.ASTReturnStatement;
import net.sourceforge.pmd.lang.java.ast.ASTWhileStatement;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

public class WhileTrueOrFalseLoopsMustBeForbidden extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {

        Node nodeExpr;
        Node nodeStatement;
        nodeExpr = (Node)node.jjtGetChild(0);
        nodeStatement = (Node)node.jjtGetChild(1);
        if (hasTrueOrFalseExpression(nodeExpr) && !hasReturnStatement(nodeStatement) && !hasBreakStatement(nodeStatement)) {

            addViolation(data, node);
        }
        return super.visit(node,data);
    }

    private boolean hasTrueOrFalseExpression(Node nodeExpr) {
        return nodeExpr.hasDescendantOfType(ASTBooleanLiteral.class);
    }

    private boolean hasReturnStatement(Node nodeStatement){
        return nodeStatement.hasDescendantOfType(ASTReturnStatement.class);
    }

    private boolean hasBreakStatement(Node nodeStatement){
        return nodeStatement.hasDescendantOfType(ASTBreakStatement.class);
    }

}
