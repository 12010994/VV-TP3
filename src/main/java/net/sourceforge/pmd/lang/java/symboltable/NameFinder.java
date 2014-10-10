/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package net.sourceforge.pmd.lang.java.symboltable;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import net.sourceforge.pmd.PMD;
import net.sourceforge.pmd.lang.java.ast.ASTArguments;
import net.sourceforge.pmd.lang.java.ast.ASTMemberSelector;
import net.sourceforge.pmd.lang.java.ast.ASTName;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryExpression;
import net.sourceforge.pmd.lang.java.ast.ASTPrimaryPrefix;
import net.sourceforge.pmd.lang.java.ast.ASTPrimarySuffix;
import net.sourceforge.pmd.lang.java.ast.JavaNode;

public class NameFinder {

    private List<NameOccurrence> names = new ArrayList<NameOccurrence>();

    public NameFinder(ASTPrimaryExpression node) {
        ASTPrimaryPrefix prefix = (ASTPrimaryPrefix) node.jjtGetChild(0);
        if (prefix.usesSuperModifier()) {
            add(new NameOccurrence(prefix, "super"));
        } else if (prefix.usesThisModifier()) {
            add(new NameOccurrence(prefix, "this"));
        }
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            checkForNameChild((JavaNode)node.jjtGetChild(i));
        }
    }

    public List<NameOccurrence> getNames() {
        return names;
    }

    private void checkForNameChild(JavaNode node) {
        if (node.getImage() != null) {
            add(new NameOccurrence(node, node.getImage()));
        }
        if (node.jjtGetNumChildren() > 0 && node.jjtGetChild(0) instanceof ASTName) {
            ASTName grandchild = (ASTName) node.jjtGetChild(0);
            for (StringTokenizer st = new StringTokenizer(grandchild.getImage(), "."); st.hasMoreTokens();) {
                add(new NameOccurrence(grandchild, st.nextToken()));
            }
        }
        if (node instanceof ASTPrimarySuffix) {
            ASTPrimarySuffix suffix = (ASTPrimarySuffix) node;
            if (suffix.isArguments()) {
                NameOccurrence occurrence = names.get(names.size() - 1);
                occurrence.setIsMethodOrConstructorInvocation();
                ASTArguments args = (ASTArguments) ((ASTPrimarySuffix) node).jjtGetChild(0);
                occurrence.setArgumentCount(args.getArgumentCount());
            } else if (suffix.jjtGetNumChildren() == 1 && suffix.jjtGetChild(0) instanceof ASTMemberSelector) {
                add(new NameOccurrence((JavaNode)suffix.jjtGetChild(0), suffix.jjtGetChild(0).getImage()));
            }
        }
    }

    private void add(NameOccurrence name) {
        names.add(name);
        if (names.size() > 1) {
            NameOccurrence qualifiedName = names.get(names.size() - 2);
            qualifiedName.setNameWhichThisQualifies(name);
        }
    }


    @Override
    public String toString() {
    	StringBuilder result = new StringBuilder();
        for (NameOccurrence occ: names) {
            result.append(occ);
            result.append(PMD.EOL);
        }
        return result.toString();
    }
}