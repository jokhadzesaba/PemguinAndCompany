package pgdp.tree;

import java.util.*;

public class Tree<T> {

    private Node<T> root;

    public Tree (T data) {
        root = new Node<T>(data);
    }
    private int depth(Node<T> current){

        return 0;
    }
    private Node<T> findT(T value)
    {
        return findT(root,new Node<T>(value));
    }
    private Node<T> findT(Node<T> current, Node<T> value){
        if (!value.equals(current) && current.isLeaf())
            return null;
        if (value.equals(current))
            return current;
        return current.getChildren().stream().map(c->findT(c, value)).filter( c-> value.equals(c)).findFirst().orElse(null);
    }
    public void insert(T value, T parent) {
        if (findT(value) != null)
            return;
        Node<T> p = findT(parent);
        if (p == null)
            return;
        p.insert(new Node<T>(value));
    }

    public void remove(T value)
    {
        if(root.getData().equals(value))
            return;
        Node<T> node = findT(value);
        if (node==null)
            return;
        node.remove();
    }

    public T LCA(T a, T b) {
        Node<T> nodeA = findT(a);
        Node<T> nodeB =findT(b);
        if (nodeA == null || nodeB == null)
            return null;
        ArrayList<T> lst1 = new ArrayList<>();
        ArrayList<T> lst2 = new ArrayList<>();
        lst(nodeA, lst1);
        lst(nodeB, lst2);
        for (var i: lst1) {
            for (var j :lst2) {
                if (i.equals(j))
                    return i;
            }
        }
        return null;
    }
    private void lst(Node<T> currnet, ArrayList<T> ls){
        if (currnet.getData().equals(root.getData())){
            ls.add(root.getData());
            return;
        }
        ls.add(currnet.getData());
        lst(currnet.getParent(), ls);
    }
    public int distanceBetweenNodes(T a, T b) {
        Node<T> nodeA = findT(a);
        Node<T> nodeB = findT(b);

        ArrayList<T> lst1 = new ArrayList<>();
        ArrayList<T> lst2 = new ArrayList<>();
        lst(nodeA, lst1);
        lst(nodeB, lst2);
        T lca = LCA(a, b);
        int counter = 0;
        for (var i: lst1) {
            if (i.equals(lca))
                break;
            counter++;
        }
        for (var i: lst2) {
            if (i.equals(lca))
                break;
            counter++;
        }
        return counter;
    }

    public Node<T> getRoot() {
        return this.root;
    }
    public boolean containsKey(T key) {
        return findT(key) != null;
    }
    public void traversal()
    {
        root.traversal(root);
    }
}
