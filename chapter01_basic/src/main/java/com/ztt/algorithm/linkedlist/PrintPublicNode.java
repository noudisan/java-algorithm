package com.ztt.algorithm.linkedlist;

/**
 * 打印两个有序链表的公共部分
 * 题目：
 * 给定两个有序的链表的头指针head1和head2,打印两个链表的公共部分
 * <p>
 * 解：因为是有序链表，所有两个链表的头开始如下判断
 * 1.如果head1的值小于head2,则head1向下移动
 * 2.如果head1的值大于head2,则head2向下移动
 * 3.如果head1等于haed2，则打印这个值，然后head1和head2都向下移动
 */
public class PrintPublicNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static void PrintPublicValue(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        Node head2 = new Node(3);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        head1.next = node1;
        node1.next = node2;
        node2.next = node3;
        head2.next = node4;

        PrintPublicValue(head1, head2);
    }
}