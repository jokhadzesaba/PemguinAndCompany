package pgdp;

import pgdp.tree.Node;
import pgdp.tree.Tree;

public class Main {
    public static void main(String[] args) {
        System.out.println("-------------");
        var tree = new Tree<Integer>(5);
        tree.insert(6,5);
        tree.insert(11,6);
        tree.insert(12,6);
        tree.insert(13,6);
        tree.insert(14,6);
        tree.insert(7,5);
        tree.insert(8,7);
        tree.insert(9,7);
        tree.insert(10,7);
        tree.insert(15,12);
        tree.insert(16,12);

//        tree.remove(12);

//        tree.traversal();
        System.out.println(tree.distanceBetweenNodes(12,12));

    }
}
