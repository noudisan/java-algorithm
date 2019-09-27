package com.ztt.structure.learnstack;

import java.util.Stack;

/**
 * 额外空间O(1)
 *
 */
public class LinkedStackWithMin2 {
    private Stack<Integer> diff = new Stack<Integer>();
    private int minValue;

    public void push(int x) {
        if (diff.isEmpty()) {
            minValue = x;
            diff.push(0);
        } else {
            int compare = x - minValue;
            diff.push(compare);
            minValue = compare < 0 ? x : minValue;
        }
    }

    public void pop() {
        int top = diff.peek();
        minValue = top < 0 ? (minValue - top) : minValue;
        diff.pop();
    }

    public int top() {
        int top = diff.peek();
        return top > 0 ? top + minValue : minValue;
    }

    public int getMin() {
        return minValue;
    }
}
