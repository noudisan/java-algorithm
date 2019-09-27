package com.ztt.algorithm.stack;

import java.util.LinkedList;

/**
 * 最大值减去最小值小于或等于 num 的子数组数量
 * 【题目】
 * 给定数组 arr 和整数 num,共返回有多少个子数组满足如下情况:
 * max(arr[i..j]) - min(arr[i..j]) <= num
 * max(arr[i..j])表示子数组 arr[i..j]中的最大值,min(arr[i..j])表示子数组 arr[i..j]中的最 小值。
 * 【要求】
 * 如果数组长度为 N,请实现时间复杂度为 O(N)的解法。
 * <p>
 * 这道题的算法原型是计算窗口内最大值的算法题（就是我上一篇leetcode的题），这里不再说了：
 * 我们用两个指针（i，j）分别代表窗口的左边界和右边界，窗口也就是子数组；
 * 用两个双端队列分别维护这个窗口的最大值和最小值；
 * 当窗口扩大时，即j向右扩展时，窗口内的最大值只会越来越大，而最小值只会越来越小（否则就会等于原来的max和min了），此时，如果max-min>num，则j不应该向右扩展了，因为max-min的范围只会越来越大；
 * 这时，应该让窗口缩小，即i向右扩展，这样max可能会减小，min可能会增大（因为原来的max和min可能会失效，即不在窗口内），这样max-min才会有<=num的可能；
 * 依次遍历数组，i，j都是从左到右，所以算法时间复杂度为O(N)；
 */
public class TotalNum {
    public static void main(String[] args) {
        System.out.println(getNum(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, 3));
    }

    public static int getNum(Integer[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        LinkedList<Integer> qmax = new LinkedList<Integer>();
        LinkedList<Integer> qmin = new LinkedList<Integer>();

        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                while (!qmax.isEmpty() && arr[j] >= arr[qmax.peekLast()]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                while (!qmin.isEmpty() && arr[j] <= qmin.peekLast()) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                j++;
            }
            // 如果等于i，因为i将要移除，所以如果max/min中含有此数，则需要移除
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            res += j - i;
            i++;
        }


        return res;
    }
}