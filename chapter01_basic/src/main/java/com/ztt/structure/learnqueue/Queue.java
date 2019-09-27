package com.ztt.structure.learnqueue;

public class Queue<E> {

    private int front;

    private int rear;

    private static final int DEFAULT_SIZE = 10;

    private Object[] queue;

    private int size;

    public Queue() {
        queue = new Object[DEFAULT_SIZE];
        size = 0;
        front = 0;
        rear = -1;
    }

    public Queue(int initSize) {
        queue = new Object[initSize];
        size = 0;
        front = 0;
        rear = -1;
    }

    /**
     * 入队操作
     *
     * @param e 待插入队列的对象
     * @return 插入成功返回true，否则返回false
     */
    public boolean add(E e) {
        if (isFull()) {
            return false;
        }
        if (rear == queue.length - 1) {
            rear = -1;
        }
        queue[++rear] = e;
        size++;
        return true;
    }

    /**
     * 出队操作
     *
     * @return
     */
    public E remove() {
        if (isEmpty()) {
            return null;
        }
        E temp = peek();
        if (front == queue.length - 1) {
            front = 0;
        }
        queue[front++] = null;
        size--;
        return temp;
    }

    /**
     * 查看队列的对头对象
     *
     * @return
     */
    public E peek() {
        if (size() > 0) {
            return (E) queue[front];
        }
        return null;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.add(1);
        queue.add(11);
        queue.add(111);
        queue.add(1111);

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
