package com.ztt.algorithm.str;

/**
 * 要求2.整体思路是先将chas排序，排序后相同的字符就放在一起，然后判断有没有重复字符就会变得非常容易，所以问题的关键是选择什么样的排序算法。
 *
 * 因为必须保证额外空间复杂度为O（1），所以此要求主要衡量对经典排序算法在额外空间复杂度方面的理解程度。
 * 首先，任何时间复杂度为O（N）的排序算法做不到额外空间复杂度为O（1），因为这些排序算法不是基于比较的排序算法，
 * 所以有多少个数都得都得“装下”,然后按照一定顺序“倒出”来完成排序。
 *
 * 具体细节请查阅有关桶排序、基数排序、基数排序等内容。然后看时间复杂度O（NlogN）的排序算法，常见的有归并排序，快速排序，希尔排序和堆排序。
 * 归并排序首先被排除，因为归并排序中有两个小组合并成一个大组的过程，这个过程需要辅助数组才能完成，尽管归并排序可以使用手摇算法将额外空间复杂度降至O（1）
 * ，但这样最差情况下的时间复杂度会因此上升至O（N^2）。快速排序也被排除，因为无论选择递归实现还是非递归实现，快速排序的额外空间复杂度最低，为O（logN），
 * 不能达到O（1）的程度。希尔排序同样被排除，因为希尔排序的时间复杂度并不固定，成败完全在于步长的选择，如果选择不当，时间复杂度会变为O（N^2）。这四种经典排序中，
 * 只有堆排序可以做到额外空间复杂度为O（1）的情况下，时间复杂度还能稳定地保持O（NlogN）.那么堆排序就是答案，似乎只要写出堆排序的大体过程，
 * 要求2就能完成。但遗憾的是，虽然堆排序的确是答案，但大部分资料提供的堆排序的实现却是基于递归函数实现的。
 * 而我们知道递归函数需要使用函数栈空间，这样堆排序的额外空间复杂度就增加至O（logN）.
 * 所以，如果真正想达到要求2的实现，则需要用非递归的方式实现堆排序。要求2的实现请看如下代码其中的headSort方法是堆排序的非递归实现。
 */
public class UniqueCharArray2 {
    public static boolean isUnique2(char[] chas) {
        if (chas == null) {
            return true;
        }

        heapSort(chas);

        for (int i = 1; i < chas.length; i++) {
            if (chas[i] == chas[i - 1]) {
                return false;
            }
        }
        return true;
    }

    private static void heapSort(char[] chas) {
        for (int i = 0; i < chas.length; i++) {
            heapInsert(chas, i);
        }
        for (int i = chas.length - 1; i > 0; i--) {
            swap(chas, 0, 1);
            heapify(chas, 0, 1);
        }
    }

    private static void heapify(char[] chas, int i, int size) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;
        while (left < size) {
            if (chas[left] > chas[i]) {
                largest = left;
            }
            if (right < size && chas[right] > chas[largest]) {
                largest = right;
            }
            if (largest != i) {
                swap(chas, largest, i);
            } else {
                break;
            }
            i = largest;
            left = i * 2 + 1;
            right = i * 2 + 2;
        }
    }

    private static void heapInsert(char[] chas, int i) {
        int parent = 0;
        while (i != 0) {
            parent = (i - 1) / 2;
            if (chas[parent] < chas[i]) {
                swap(chas, parent, i);
                i = parent;
            } else {
                break;
            }
        }
    }

    private static void swap(char[] chas, int index1, int index2) {
        char tmp = chas[index1];
        chas[index1] = chas[index2];
        chas[index2] = tmp;
    }

    public static void main(String[] args) {
        String a = "qwertyuiopw";

        System.out.println(UniqueCharArray2.isUnique2(a.toCharArray()));
    }
}
