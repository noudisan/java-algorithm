package com.ztt.algorithm.stack;

import java.util.Stack;

/**
 * 如何仅用递归函数和栈操作逆序一个栈
 * 题目
 * 一个栈依次压入1,2,3,4,5那么从栈顶到栈底分别为5,4,3,2,1。将这个栈转置后，
 * 从栈顶到栈底为1,2,3,4,5，也就是实现栈中元素的逆序，但是只能用递归函数来实现，而不能用另外的数据结构。
 * 解答
 * 本题考察栈的操作和递归函数的设计，我们需要设计出两个递归函数。
 */
public class ReverseStack {

    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }
    }

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }

        int i = getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }
}