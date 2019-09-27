package com.ztt.structure.learnlinkedlist;

/**
 * #循环链表
 循环链表是另一种形式的链式存贮结构。它的特点是表中最后一个结点的指针域指向头结点，整个链表形成一个环。

 分类
 （1）单循环链表——在单链表中，将终端结点的指针域NULL改为指向表头结点或开始结点即可。
 （2）多重链的循环链表——将表中结点链在多个环上。[1]

 空链判断
 判断空链表的条件是
 head==head->next;
 rear==rear->next;

 尾指针
 用尾指针rear表示的单循环链表对开始结点a1和终端结点an查找时间都是O(1)。
 而表的操作常常是在表的首尾位置上进行，因此，实用中多采用尾指针表示单循环链表。带尾指针的单循环链表可见下图。
 注意：判断空链表的条件为rear==rear->next;
 */
public class CircularLinkedList {

    class Element {
        public Object value = null;
        private Element next = null;
    }

    private Element header = null;//头结点

    /**
     * 初始化链表
     */
    void initList() {
        header = new Element();
        header.value = null;
        header.next = header;
    }

    /**
     * 插入链表
     */
    void insertList(Object o) {
        Element e = new Element();
        e.value = o;
        if (header.next == header)//第一次插入元素
        {
            header.next = e;
            e.next = header;
        } else//不是第一次插入元素
        {
            //temp引用在栈中，temp和header引用都指向堆中的initList()中new的Element对象
            Element temp = header;
            while (temp.next != header)//寻找最后一个元素
            {
                temp = temp.next;
            }
            temp.next = e;
            e.next = header;//新插入的最后一个节点指向头结点
        }
    }

    /**
     * 删除链表中第i个元素
     */
    void deletelist(Object o) {
        Element temp = header;
        while (temp.next != header) {
            //判断temp当前指向的结点的下一个结点是否是要删除的结点
            if (temp.next.value.equals(o)) {
                temp.next = temp.next.next;//删除结点
            } else {
                temp = temp.next;//temp“指针”后移
            }
        }
    }

    /**
     * 获取链表的第i个位置的元素
     */
    Element getElement(int i) {
        if (i <= 0 || i > size()) {
            System.out.println("获取链表的位置有误！返回null");
            return null;
        } else {
            int count = 0;
            Element element = new Element();
            Element temp = header;
            while (temp.next != header) {
                count++;
                if (count == i) {
                    element.value = temp.next.value;
                }
                temp = temp.next;
            }
            return element;
        }
    }

    /**
     * 链表长度
     */
    int size() {
        Element temp = header;
        int size = 0;
        while (temp.next != header) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 判断链表中是否存在某元素
     */
    Boolean isContain(Object o) {
        Element temp = header;
        while (temp.next != header) {
            if (temp.next.value.equals(o)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    /**
     * 打印链表
     */
    void print() {
        System.out.print("打印链表：");
        Element temp = header;
        while (temp.next != header) {
            temp = temp.next;
            System.out.print(temp.value + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CircularLinkedList clList = new CircularLinkedList();
        clList.initList();
        clList.insertList(1);
        clList.insertList(2);
        clList.insertList(3);
        clList.insertList(4);
        clList.insertList(5);
        clList.print();

        System.out.println("链表长度：" + clList.size());
        clList.deletelist(1);
        clList.deletelist(5);
        clList.print();
        System.out.println("第1个元素值为：" + clList.getElement(1).value);
        System.out.println("第2个元素值为：" + clList.getElement(2).value);
        System.out.println("第3个元素值为：" + clList.getElement(3).value);

        System.out.println(clList.isContain(2));
        System.out.println(clList.isContain(6));
//      System.out.println(clList.isContain(5));
    }
}
