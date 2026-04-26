import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node>{

    private Node root;
    private int size; //1) Add size

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        //3) Accessible key and value for iteration
        public K getKey() {
            return key;
        }

        public V getValue() {
            return val;
        }
    }

    //1) Add size
    public int size() {
        return size;
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    //Helper method
    private Node put(Node node, K key, V val) {
        if (node == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, val);
        else if (cmp > 0) node.right = put(node.right, key, val);
        else node.val = val; // Update if exists
        return node;
    }

    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.val;
    }

    //Helper method
    private Node get(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return get(node.left, key);
        else if (cmp > 0) return get(node.right, key);
        else return node;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    //Helper method
    private Node delete(Node node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                size--;
                return node.right;
            }
            if (node.right == null) {
                size--;
                return node.left;
            }

            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    }

    //Helper method
    private Node min(Node node) {
        if (node.left == null) return node;
        else return min(node.left);
    }

    //Helper method
    private Node deleteMin(Node node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    //2) Implement in-order traversal for iterator()
    @Override
    public Iterator<Node> iterator() {
        List<Node> nodes = new ArrayList<>();
        inOrderTraversal(root, nodes);
        return nodes.iterator();
    }

    private void inOrderTraversal(Node node, List<Node> nodes) {
        if (node == null) return;
        inOrderTraversal(node.left, nodes);
        nodes.add(node); // In-order: Left, Root, Right
        inOrderTraversal(node.right, nodes);
    }
}