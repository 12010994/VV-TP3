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
            //ajout de la violation
            addViolation(data, node);
        }
        return super.visit(node,data);
    }

    private boolean hasWhileStatement(Node node) {
//        for(int i=0; i< node.jjtGetNumChildren(); i++){
//            if (node.jjtGetChild(i) instanceof ASTWhileStatement) {
//                return true;
//            }
//        }
        List<ASTWhileStatement> childrenOfType = node.findDescendantsOfType(ASTWhileStatement.class);
        if (childrenOfType == null || childrenOfType.isEmpty()) {
            return false;
        }

        System.out.println("boucle imbriquée: "+childrenOfType.size());
        return true;
    }
}
