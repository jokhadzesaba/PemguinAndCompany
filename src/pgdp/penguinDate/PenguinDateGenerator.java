package pgdp.penguinDate;

import pgdp.tree.Tree;

public class PenguinDateGenerator {

    private Tree<Penguin> tree;

    public PenguinDateGenerator(Tree<Penguin> tree) {
        this.tree = tree;
    }

    public boolean canGoOnADate(Penguin p1, Penguin p2) {
        Penguin lcaPenguin = tree.LCA(p1,p2);
        System.out.println(tree.distanceBetweenNodes(lcaPenguin, p1));
        System.out.println(tree.distanceBetweenNodes(lcaPenguin, p2));
        return tree.distanceBetweenNodes(lcaPenguin, p1) >= p1.getAllowance() && tree.distanceBetweenNodes(lcaPenguin, p2) >= p2.getAllowance();
    }

    public Tree<Penguin> getTree() {
        return this.tree;
    }

}
