package CP_Notes;

import java.util.Random;

class TreapNode {
    int key, priority;
    TreapNode left, right;

    public TreapNode(int key) {
        this.key = key;
        this.priority = new Random().nextInt();
        this.left = this.right = null;
    }
}

class Treap {
    private TreapNode root;

    private TreapNode rotateRight(TreapNode y) {
        TreapNode x = y.left;
        TreapNode T = x.right;
        x.right = y;
        y.left = T;
        return x;
    }

    private TreapNode rotateLeft(TreapNode y) {
        TreapNode x = y.right;
        TreapNode T = x.left;
        x.left = y;
        y.right = T;
        return x;
    }

    private TreapNode insert(TreapNode root, int key) {
        if (root == null) return new TreapNode(key);

        if (key < root.key) {
            root.left = insert(root.left, key);
            if (root.left.priority > root.priority) {
                root = rotateRight(root);
            }
        } else {
            root.right = insert(root.right, key);
            if (root.right.priority > root.priority) {
                root = rotateLeft(root);
            }
        }
        return root;
    }

    public void insert(int key) {
        root = insert(root, key);
    }

    private TreapNode delete(TreapNode root, int key) {
        if (root == null) return null;

        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            if (root.left.priority > root.right.priority) {
                root = rotateRight(root);
                root.right = delete(root.right, key);
            } else {
                root = rotateLeft(root);
                root.left = delete(root.left, key);
            }
        }
        return root;
    }

    public void delete(int key) {
        root = delete(root, key);
    }

    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(TreapNode root, int key) {
        if (root == null) return false;
        if (root.key == key) return true;
        return key < root.key ? search(root.left, key) : search(root.right, key);
    }

    public void inorder() {
        inorder(root);
        System.out.println();
    }

    private void inorder(TreapNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print("(" + root.key + ", " + root.priority + ") ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {
        Treap treap = new Treap();

        treap.insert(50);
        treap.insert(30);
        treap.insert(20);
        treap.insert(40);
        treap.insert(70);
        treap.insert(60);
        treap.insert(80);

        System.out.println("Treap after insertion:");
        treap.inorder();

        System.out.println("Search 40: " + treap.search(40));
        System.out.println("Search 100: " + treap.search(100));

        treap.delete(50);
        System.out.println("Treap after deleting 50:");
        treap.inorder();
    }
}