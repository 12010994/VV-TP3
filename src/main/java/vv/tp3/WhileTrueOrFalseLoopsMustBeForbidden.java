package vv.tp3;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ecmascript.ast.ASTName;
import net.sourceforge.pmd.lang.java.ast.*;
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
        Node node=nodeExpr.jjtGetChild(0);
        if(node instanceof ASTPrimaryExpression){
            System.out.println("PrimaryExpression");
            node=node.jjtGetChild(0);
            if(node instanceof ASTPrimaryPrefix){
                System.out.println("    PrimaryPrefix");
                node=node.jjtGetChild(0);
                if(node instanceof ASTName){ //Pour question 3...
                    System.out.println("        Name");
                    if(node.getImage().equals(true)){
                        return true;
                    }
                }
                if(node instanceof ASTLiteral){
                    System.out.println("        Literal");
                    node=node.jjtGetChild(0);
                    if(node instanceof ASTBooleanLiteral){
                        System.out.println("            BooleanLiteral");
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
