package com.ztt.algorithm.stack;

import java.util.Stack;

/**
 * 由两个栈组成的队列
 题目
 编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll、peek）。

 解答
 两个栈，一个压入栈一个弹出栈。
 注意：
 1.如果stackPush要往stackPop中压入数据，那么必须一次性把stackPush中的数据全部压入。
 2.如果stackPop不为空，stackPop绝对不能向stackPush中压入数据。
 */
public class TwoStacksQueue {

    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStacksQueue() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    public void add(Integer pushInt) {
        stackPush.push(pushInt);
    }

    public int poll() {
        if (stackPush.empty() && stackPop.empty()) {
            throw new RuntimeException("stack is empty");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.pop();
    }

    public int peek() {
        if (stackPush.empty() && stackPop.empty()) {
            throw new RuntimeException("stack is empty");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }

}
