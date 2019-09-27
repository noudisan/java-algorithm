package com.ztt.structure.learnstack;

import java.util.Stack;

/**
 * 实现一个栈,带有出栈,入栈,取最小元素,保证三个方法的负载度都是O(1)
 */
public class LinkedStackWithMin {

    private Stack<Integer> data = new Stack<Integer>();
    private Stack<Integer> minValue = new Stack<Integer>();

    public void push(int x) {
        data.push(x);
        if (minValue.isEmpty() || x <= minValue.peek())
            minValue.push(x);
    }

    public void pop() {
        int value = data.pop();
        if (value == minValue.peek())
            minValue.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minValue.peek();
    }

    public int getMinSize() {
        return minValue.size();
    }

    public static void main(String[] args) {
        LinkedStackWithMin linkedStackWithMin = new LinkedStackWithMin();
        int[] array = new int[]{2, 1, 3, 4, -2, 0, -2};

        for (int i : array) {
            linkedStackWithMin.push(i);
        }


        System.out.println("minValue:" + linkedStackWithMin.getMin());
        System.out.println("minValueSize:" + linkedStackWithMin.getMinSize());// 2,1,-2,-2

        linkedStackWithMin.pop();
        linkedStackWithMin.pop();

        System.out.println(linkedStackWithMin.getMin());


    }
}
