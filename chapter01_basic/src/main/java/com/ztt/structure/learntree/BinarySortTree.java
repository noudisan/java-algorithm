package com.ztt.structure.learntree;

public class BinarySortTree {

    public static void main(String args[]) {
        int data[] = {12, 11, 34, 45, 67, 89, 56, 43, 22, 98};
        TreeNode root = new TreeNode(data[0]);

        System.out.print("二叉树的中的数据：");
        for (int i = 1; i < data.length; i++) {
            root.insertTree(root, data[i]);
            System.out.print(data[i - 1] + ";");
        }
        System.out.println(data[data.length - 1]);

        BinarySortTree b = new BinarySortTree();

        int key = 22;
        if (b.searchKey(root, key)) {
            System.out.println("找到了:" + key);
        } else {
            System.out.println("没有找到：" + key);
        }
    }

    public boolean searchKey(TreeNode root, int key) {
        boolean bl = false;
        if (root == null) {
            bl = false;
            return bl;
        } else if (root.data == key) {
            bl = true;
            return bl;
        } else if (key >= root.data) {
            return searchKey(root.rightNode, key);
        }
        return searchKey(root.leftNode, key);
    }


    private static class TreeNode {
        int data;
        TreeNode leftNode;
        TreeNode rightNode;

        TreeNode(int data) {
            this.data = data;
            leftNode = null;
            rightNode = null;
        }

        public void insertTree(TreeNode root, int data) {
            if (data >= root.data) {
                if (root.rightNode == null) {
                    root.rightNode = new TreeNode(data);
                } else {
                    insertTree(root.rightNode, data);
                }
            } else {
                if (root.leftNode == null) {
                    root.leftNode = new TreeNode(data);
                } else {
                    insertTree(root.leftNode, data);
                }
            }
        }
    }
}
