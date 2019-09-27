package com.ztt.algorithm.stack;

/**
 * 用栈来求解汉诺塔问
 * 题目
 * 在汉诺塔规则的基础上，限制不能从最左的塔移动到最右的塔上，必须经过中间的塔，
 * 移动的跨度只能是一个塔。当塔有N层的时候，打印最优移动过程和最优移动步数。
 * <p>
 * 要求
 * 方法一：使用递归的方法进行移动
 * <p>
 * 方法二：使用栈进行移动
 * <p>
 * 解答思路
 * 方法一：
 * 无论多少层，都看作有两层，最大的一层(命名为X)、(N-1)层合并起来的作为一层(命名为Y)，目标是将X移动到最右侧，然后再把Y移动到最右侧。
 */
public class HanoiProblem1 {

    public static void main(String[] args) {
        int num = 3;

        // solution 1
        int steps1 = hanoiProblem1(num, "left", "mid", "right");
        System.out.println("It will move " + steps1 + " steps.");
    }

    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }
        return process(num, left, mid, right, left, right);
    }

    public static int process(int num, String left, String mid, String right, String from, String to) {
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                System.out.println("Move 1 from " + from + " to " + to);
                return 1;
            } else {
                System.out.println("Move 1 from " + from + " to " + mid);
                System.out.println("Move 1 from " + mid + " to " + to);
                return 2;
            }
        }
        if (from.equals(mid) || to.equals(mid)) {
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        } else {
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println("Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println("Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);
            return part1 + part2 + part3 + part4 + part5;
        }
    }
}
