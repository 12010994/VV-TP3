package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.*;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;

import java.util.List;

public class WhileLoopsMustNotInterlockRule extends AbstractJavaRule {

    public Object visit(ASTWhileStatement node, Object data) {

        Node firstStmt;
        firstStmt = (Node)node.jjtGetChild(1);
        if (hasWhileStatement(firstStmt)) {
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

    private boolean hasWhileStatement(Node node) {
        return node.hasDescendantOfType(ASTWhileStatement.class);
    }
}
