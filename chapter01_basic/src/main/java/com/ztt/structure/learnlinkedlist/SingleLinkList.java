package com.ztt.structure.learnlinkedlist;

/**
 * #单向链表（单链表）
 是链表的一种，其特点是链表的链接方向是单向的，对链表的访问要通过顺序读取从头部开始；
 链表是使用指针进行构造的列表；又称为结点列表，因为链表是由一个个结点组装起来的；其中每个结点都有指针成员变量指向列表中的下一个结点；
 列表是由结点构成，head指针指向第一个成为表头结点，而终止于最后一个指向nuLL的指针；

 ##链表的优点
 相比较普通的线性结构，链表结构的可以总结一下：
 （1）单个结点创建非常方便，普通的线性内存通常在创建的时候就需要设定数据的大小
 （2）结点的删除非常方便，不需要像线性结构那样移动剩下的数据
 （3）结点的访问方便，可以通过循环或者递归的方法访问到任意数据，但是平均的访问效率低于线性表

 */
public class SingleLinkList {

    public Node first; // 定义一个头结点
    private int pos = 0;// 节点的位置

    public SingleLinkList() {
        this.first = null;
    }

    // 插入一个头节点
    public void addFirstNode(int data) {
        Node node = new Node(data);
        node.next = first;
        first = node;
    }

    // 删除一个头结点,并返回头结点
    public Node deleteFirstNode() {
        Node tempNode = first;
        first = tempNode.next;
        return tempNode;
    }

    // 在任意位置插入节点 在index的后面插入
    public void add(int index, int data) {
        Node node = new Node(data);
        Node current = first;
        Node previous = first;
        while (pos != index) {
            previous = current;
            current = current.next;
            pos++;
        }
        node.next = current;
        previous.next = node;
        pos = 0;
    }

    // 删除任意位置的节点
    public Node deleteByPos(int index) {
        Node current = first;
        Node previous = first;
        while (pos != index) {
            pos++;
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            pos = 0;
            previous.next = current.next;
        }
        return current;
    }

    // 根据节点的data删除节点(仅仅删除第一个)
    public Node deleteByData(int data) {
        Node current = first;
        Node previous = first; //记住上一个节点
        while (current.data != data) {
            if (current.next == null) {
                return null;
            }
            previous = current;
            current = current.next;
        }
        if (current == first) {
            first = first.next;
        } else {
            previous.next = current.next;
        }
        return current;
    }

    // 显示出所有的节点信息
    public void displayAllNodes() {
        Node current = first;
        while (current != null) {
            current.display();
            current = current.next;
        }
        System.out.println();
    }

    // 根据位置查找节点信息
    public Node findByPos(int index) {
        Node current = first;
        if (pos != index) {
            current = current.next;
            pos++;
        }
        return current;
    }

    // 根据数据查找节点信息
    public Node findByData(int data) {
        Node current = first;
        while (current.data != data) {
            if (current.next == null)
                return null;
            current = current.next;
        }
        return current;
    }

    //节点类
    private static class Node {
        protected Node next; //指针域
        protected int data;//数据域

        public Node(int data) {
            this.data = data;
        }

        //显示此节点
        public void display() {
            System.out.print(data + " ");
        }
    }


    public static void main(String[] args) {
        SingleLinkList singleLinkList = new SingleLinkList();
        singleLinkList.addFirstNode(20);
        singleLinkList.addFirstNode(21);
        singleLinkList.addFirstNode(19);
        //19,21,20
        singleLinkList.add(1, 22); //19,22,21,20
        singleLinkList.add(2, 23); //19,22,23,21,20
        singleLinkList.add(3, 99); //19,22,23,99,21,20
        singleLinkList.displayAllNodes();
        Node node = singleLinkList.deleteByData(19);
        System.out.println("node : " + node.data);
        singleLinkList.displayAllNodes();
        Node node1 = singleLinkList.findByPos(0);
        System.out.println("node1: " + node1.data);
        Node node2 = singleLinkList.findByData(22);
        System.out.println("node2: " + node2.data);
    }

}