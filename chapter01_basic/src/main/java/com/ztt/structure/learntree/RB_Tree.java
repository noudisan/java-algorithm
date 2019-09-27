package com.ztt.structure.learntree;


import com.ztt.utils.Utilities;

/**
 * 一、简介
 红黑树是一种自平衡二叉查找树(对称二叉B树)，是在计算机科学中用到的一种数据结构，典型的用途是实现关联数组。它是复杂的，但它的操作有着良好的最坏情况运行时间，并且在实践中是高效的： 它可以在O(log n)时间内做查找，插入和删除，这里的n 是树中元素的数目。

 红黑树的操作时很复杂的，其大致过程和普通的二叉查找树基本一致，但是主要的难点就在于插入和删除操作时要想办法恢复红黑树的性质。这时的情况会很多，但主要的方式就是之前说的左旋和右旋。理论上的东西说起来太麻烦了，去看看《算法导论》里面的介绍吧，


 二、性质
 红黑树是每个节点都带有颜色属性的二叉查找树，颜色或红色或黑色。
 在二叉查找树强制一般要求以外，对于任何有效的红黑树我们增加了如下的额外要求:
 性质1. 节点是红色或黑色。
 性质2. 根节点是黑色。
 性质3 每个叶节点（NIL节点，空节点）是黑色的。
 性质4 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
 性质5. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。

 这些约束强制了红黑树的关键性质: 从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。结果是这个树大致上是平衡的。因为操作比如插入、删除和查找某个值的最坏情况时间都要求与树的高度成比例，这个在高度上的理论上限允许红黑树在最坏情况下都是高效的，而不同于普通的二叉查找树。
 要知道为什么这些特性确保了这个结果，
 注意到性质4导致了路径不能有两个毗连的红色节点就足够了。最短的可能路径都是黑色节点，最长的可能路径有交替的红色和黑色节点。因为根据性质5所有最长的路径都有相同数目的黑色节点，这就表明了没有路径能多于任何其他路径的两倍长。

 在很多树数据结构的表示中，一个节点有可能只有一个子节点，而叶子节点不包含数据。用这种范例表示红黑树是可能的，但是这会改变一些属性并使算法复杂。为此，本文中我们使用 "nil 叶子" 或"空(null)叶子"，如上图所示，它不包含数据而只充当树在此结束的指示。这些节点在绘图中经常被省略，导致了这些树好象同上述原则相矛盾，而实际上不是这样。与此有关的结论是所有节点都有两个子节点，尽管其中的一个或两个可能是空叶子。


 */
public class RB_Tree {
    // 红黑树结点,比二叉树的结点多了一个color属性
    private static class TreeNode {
        int key;
        String color;
        TreeNode left;
        TreeNode right;
        TreeNode parent;

        public TreeNode(int key) {
            this.key = key;
        }

        public TreeNode(String color) {
            this.color = color;
            this.key = -1;// 哨兵作为叶子,值要最小
        }
    }

    // 哨兵结点即所有叶子结点和根的父结点
    private static TreeNode nil = new TreeNode("black");
    // 根结点
    private TreeNode root = nil;

    // 建立二叉查找树
    public RB_Tree(int[] ints) {
        for (int i = 1; i < ints.length; i++)
            insert(ints[i]);
    }

    // 查找结点
    public TreeNode search(int key, TreeNode node) {
        if (key == node.key)
            return node;
        else {
            if (key > node.key) {
                node = node.right;
            } else if (key < node.key)
                node = node.left;
            return search(key, node);
        }
    }

    // 插入结点
    public boolean insert(int key) {
        TreeNode newNode = new TreeNode(key);
        newNode.color = "red"; // 新插入的结点全部先赋值为红色
        TreeNode parent = nil;
        TreeNode current = root;
        while (current != nil) {
            parent = current;
            if (key < current.key)
                current = current.left;
            else if (key > current.key)
                current = current.right;
            else
                return false;
        }
        if (parent == nil) {
            root = newNode;
            root.parent = nil;
        } else {
            newNode.parent = parent;
            if (key < parent.key) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }
        newNode.left = nil;
        newNode.right = nil;
        // 恢复红黑树性质
        RB_insert_fixup(newNode);
        return true;
    }

    // 修复插入操作时破坏的红黑树性质
    public void RB_insert_fixup(TreeNode z) {
        while (z.parent.color == "red") {
            if (z.parent == z.parent.parent.left) {
                TreeNode y = z.parent.parent.right;
                // case1:z的叔叔y是红色的
                if (y.color == "red") {
                    z.parent.color = "black";
                    y.parent.color = "red";
                    y.color = "black";
                    z = z.parent.parent;
                } else {
                    // case2:z的叔叔y是黑色的，且z是右孩子
                    if (z == z.parent.right) {
                        z = z.parent;
                        left_rotate(z);
                    }
                    // case3:z的叔叔y是黑色的，且z是左孩子
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    right_rotate(z.parent.parent);
                }
            } else {// 左右的情况是对称的
                TreeNode y = z.parent.parent.left;
                if (y.color == "red") {
                    z.parent.color = "black";
                    y.parent.color = "red";
                    y.color = "black";
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        right_rotate(z);
                    }
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    left_rotate(z.parent.parent);
                }
            }
        }
        root.color = "black";
    }

    /*
    * 删除结点操作总是在只有一边有子女的结点或者叶子结点上进行的, 绝不会在一个有二个子女的结点上进行删除操作,
    * successor函数只有在节点有2个子女的时候被调用, 这个时候，该函数一定是沿节点的右子树向下进行的, 最终会找到一个只有一个孩子的结点
    */
    public void delete(int key) {
        // 定位到即将被删除的结点
        TreeNode deleteNode = search(key, root);
        // 实际被删的结点
        TreeNode delete;
        // 被删结点的唯一的孩子
        TreeNode x;
        // 被删结点没有子女或只有一个子女时,删除的就是该结点
        if (deleteNode.left == nil || deleteNode.right == nil)
            delete = deleteNode;
        else
            // 被删结点有两个子女时,删除的是该结点的后继,然后将后继的值赋给该结点
            delete = successor(deleteNode);
        if (delete.left != nil)
            x = delete.left;
        else
            x = delete.right;
        x.parent = delete.parent;
        if (delete.parent == nil) {
            root = x;
        } else {
            if (delete == delete.parent.left) {
                delete.parent.left = x;
            } else {
                delete.parent.right = x;
            }
        }
        // 注意替换key
        if (delete != deleteNode) {
            deleteNode.key = delete.key;
        }
        // 恢复红黑树性质
        if (delete.color == "black") {
            RB_delete_fixup(x);
        }
    }

    // 修复删除操作时破坏的红黑树性质
    public void RB_delete_fixup(TreeNode x) {
        /**********************************
         ************ case1 ************** / | \ *********** case2 case4 case3 ***** \
         * ****** case4 **
         **********************************/
        while (x != root && x.color == "black") {
                      /*
                      * 为保持黑高度不变,把x想象成为有两重黑色,但这显然不符合红黑性质,所以调整方向有两个：
                      * 1.将x的黑色去掉一个,这就需要将x的兄弟节点的黑高度减一,即case2
                      * 2.通过旋转使x的位置下降,将x的多余的黑色赋给其父结点,即case4
                      */
            if (x == x.parent.left) {
                TreeNode w = x.parent.right;
                // case1:x的兄弟w是红色
                if (w.color == "red") {
                    w.color = "black";
                    w.parent.color = "red";
                    left_rotate(x.parent);
                    w = x.parent.right;
                }
                // case2x:的兄弟w是黑色,且w的两个孩子是黑色
                if (w.left.color == "black" && w.right.color == "black") {
                    w.color = "red";
                    x = x.parent;
                } else {
                    // case3:x的兄弟w是黑色,w的左孩子是红色，右孩子是黑色
                    if (w.right.color == "black") {
                        w.left.color = "red";
                        w.color = "red";
                        right_rotate(w);
                        w = x.parent.right;
                    }
                    // case4:x的兄弟w是黑色,w的右孩子是红色
                    w.color = x.parent.color;
                    x.parent.color = "black";
                    w.right.color = "black";
                    left_rotate(x.parent);
                    x = root;
                }
            } else {
                TreeNode w = x.parent.left;
                if (w.color == "red") {
                    w.color = "black";
                    w.parent.color = "red";
                    right_rotate(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == "black" && w.right.color == "black") {
                    w.color = "red";
                    x = x.parent;
                } else {
                    if (w.left.color == "black") {
                        w.right.color = "red";
                        w.color = "red";
                        left_rotate(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = "black";
                    w.left.color = "black";
                    right_rotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = "black";
    }

    // 左旋
    public void left_rotate(TreeNode node) {
        TreeNode tn = node.right;
        node.right = tn.left;
        if (tn.left != nil)
            tn.left.parent = node;
        // 要时刻注意旋转时根结点的变换
        tn.parent = node.parent;
        if (node.parent == nil)
            root = tn;
        else if (node == node.parent.left)
            node.parent.left = tn;
        else
            node.parent.right = tn;
        tn.left = node;
        node.parent = tn;
    }

    // 右旋
    public void right_rotate(TreeNode node) {
        TreeNode tn = node.left;
        node.left = tn.right;
        if (tn.right != nil)
            tn.right.parent = node;
        tn.parent = node.parent;
        if (node.parent == nil)
            root = tn;
        else if (node == node.parent.left)
            node.parent.left = tn;
        else
            node.parent.right = tn;
        tn.right = node;
        node.parent = tn;
    }

    // 最小值(最大值将left换为right即可)
    public TreeNode min(TreeNode node) {
        while (node.left != nil) {
            node = node.left;
        }
        return node;
    }

    // 后继结点
    public TreeNode successor(TreeNode node) {
        if (node.right != nil) {
            return min(node.right);
        } else {
            TreeNode parent = node.parent;
            while (parent != nil && node == parent.right) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    // 前序遍历
    public void preorder() {
        preorder(root);
    }

    private void preorder(TreeNode root) {
        if (root == nil)
            return;
        System.out.print("[" + root.key + "," + root.color + "] ");
        preorder(root.left);
        preorder(root.right);
    }

    // 后序遍历
    public void postorder() {
        postorder(root);
    }

    private void postorder(TreeNode root) {
        if (root == nil)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.key + " ");
    }

    // 中序遍历
    public void inorder() {
        inorder(root);
    }

    private void inorder(TreeNode root) {
        if (root == nil)
            return;
        postorder(root.left);
        System.out.print(root.key + " ");
        postorder(root.right);
    }

    public static void main(String[] args) {
        int[] E = Utilities.createArray(10);
        for (int i = 1; i <= 20; i++) {
            System.out.print(E[i] + ",");
        }
        System.out.println();
        RB_Tree RBtree = new RB_Tree(E);
        RBtree.preorder();
        RBtree.delete(E[8]);
        System.out.println();
        RBtree.preorder();
    }
}

