package com.ztt.algorithm.stack;

import java.util.Stack;

/**
 * 用栈来求解汉诺塔问
 * 使用栈而不使用递归的方式进行移动，使用3个栈模拟3个塔，每一步的移动，都按照真实情况进行。
 * 按照规则，可能的移动动作限定为LM、ML、MR、RM四种步骤（L、M、R分布表示左中右），通过引入逆反原则和小压大原则，可以得出每次移动，只有一种可行步骤。
 */
public class HanoiProblem2 {
/*
    public static void main(String[] args) {
        int num = 4;
        // solution 1
        int steps1 = hanoiProblem2(num, "left", "mid", "right");
        System.out.println("It will move " + steps1 + " steps.");
    }
    public static int hanoiProblem2(int num, String left, String mid, String right) {
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();

        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);

        for (int i = num; i > 0; i--) {
            lS.push(i);
        }

        Action[] record = {Action.No};
        int step = 0;
        while (rS.size() != num + 1) {
            step += fStackToStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackToStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackToStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackToStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }

    public static int fStackToStack(Action[] record, Action preNoAct, Action nowAct, Stack<Integer> fStack, Stack<Integer> toStack,
                                    String from, String to) {
        if (record[0] != preNoAct && fStack.peek() < toStack.peek()) {
            toStack.push(fStack.pop());
            System.out.println("Move " + toStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }*/
}
