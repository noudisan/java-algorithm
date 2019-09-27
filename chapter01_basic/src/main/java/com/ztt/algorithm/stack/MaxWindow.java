package com.ztt.algorithm.stack;

import java.util.LinkedList;

/**
 * 窗口最大值数组
 * 【题目】
 * 给出一个整形数组，例如arr = {5,4,3,5,6,7,6}，窗口大小为w=3，窗口每次向右移动一位，
 * 输出每个窗口中最大值组成的数组。
 * [5,4,3,]5,6,7,6 窗口最大值为5
 * 5,[4,3,5,]6,7,6 窗口最大值为5
 * 5,4,[3,5,6,]7,6 窗口最大值为6
 * 5,4,3,[5,6,7,]6 窗口最大值为7
 * 5,4,3,5,[6,7,6] 窗口最大值为7
 * 则输出的数组为{5,5,6,7,7};
 * <p>
 * 【解答】
 * 对于一个给定的数组，求得的最终数组长度可以确定是arr.length - w +1,
 * 本题重点是使用双端队列来更新最大值小标，并同时将最大值存入数组中。保证时间复杂度为O(n)。
 */
public class MaxWindow {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        int[] maxWindow = getMaxWindow(arr, 3);
        for (int i : maxWindow) {
            System.out.println(i);
        }
    }

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            //保证列表中第一位是最大值下标
            while (!qmax.isEmpty() && arr[qmax.getLast()] <= arr[i]) {
                qmax.pollLast();
            }
            qmax.add(i);
            //删除过期下标
            if (qmax.getFirst() == i - w) {
                qmax.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }
}
