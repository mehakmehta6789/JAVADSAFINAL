import java.util.Comparator;
import java.util.function.Consumer;

class BSTNode<T> {
    T data;
    BSTNode<T> left, right;

    BSTNode(T data) {
        this.data = data;
    }
}

public class BST<T> {
    private BSTNode<T> root;
    private Comparator<T> comparator;

    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private BSTNode<T> insertRec(BSTNode<T> node, T data) {
        if (node == null) return new BSTNode<>(data);
        if (comparator.compare(data, node.data) < 0)
            node.left = insertRec(node.left, data);
        else
            node.right = insertRec(node.right, data);
        return node;
    }

    public T search(T key) {
        return searchRec(root, key);
    }

    private T searchRec(BSTNode<T> node, T key) {
        if (node == null) return null;
        int cmp = comparator.compare(key, node.data);
        if (cmp == 0) return node.data;
        return cmp < 0 ? searchRec(node.left, key) : searchRec(node.right, key);
    }

    public void inOrder(Consumer<T> action) {
        inOrderRec(root, action);
    }

    private void inOrderRec(BSTNode<T> node, Consumer<T> action) {
        if (node != null) {
            inOrderRec(node.left, action);
            action.accept(node.data);
            inOrderRec(node.right, action);
        }
    }

    public boolean exists(T key) {
        return search(key) != null;
    }
}
