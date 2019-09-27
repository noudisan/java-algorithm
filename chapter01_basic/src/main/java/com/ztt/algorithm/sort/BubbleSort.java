package com.ztt.algorithm.sort;

import com.ztt.utils.Utilities;

/**
 * 冒泡排序 ----交换排序的一种
 * 方法：相邻两元素进行比较，如有需要则进行交换，每完成一次循环就将最大元素排在最后（如从小到大排序），
 * 下一次循环是将其他的数进行类似操作。
 * 性能：比较次数O(n^2),n^2/2；交换次数O(n^2),n^2/4
 */
public class BubbleSort {
    // 打印出随机数
    public void printArray(int[] data) {
        for (int i : data) {
            System.out.print(i + " ");
        }

    }

    // 交换相邻两个数
    public void swap(int[] data, int x, int y) {
        int temp = data[x];
        data[x] = data[y];
        data[y] = temp;
    }

    public void bubbleSort(int[] data) {
        // 比较的轮数
        for (int i = 1; i < data.length; i++) { // 数组有多长,轮数就有多长
            // 将相邻两个数进行比较，较大的数往后冒泡
            for (int j = 0; j < data.length - i; j++) { // 每一轮下来会将比较的次数减少
                if (data[j] > data[j + 1]) {
                    // 交换相邻两个数
                    swap(data, j, j + 1);
                }
            }
        }
        printArray(data); // 输出冒泡排序后的数组值
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] array = Utilities.createArray(10);

        bubbleSort.bubbleSort(array);

    }
}
