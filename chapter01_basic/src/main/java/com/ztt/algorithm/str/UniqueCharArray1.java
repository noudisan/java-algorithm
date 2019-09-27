package com.ztt.algorithm.str;

/**
 * 给定一个字符类型数组chas[],判断chas是否所有的字符都只出现过一次，
 *
 * 若chas=['a','b','c']，返回true；chas['1','2','1'],返回false、
 * 要求1.实现时间复杂度为O（N）的方法
 * 2.在保证额外空间复杂度为O（1）的前提下，实现时间复杂度尽量低的方法。
 * 要求1：遍历一边chas，用map记录每个每种字符的出现情况，这样就可以在遍历时发现字符重复的情况，
 * map可以用长度固定的数组实现，也可以用哈希表实现。具体请看如下代码中的isUnique1方法。
 */
public class UniqueCharArray1 {
    public static boolean isUnique(char[] chas) {
        if (chas == null) {
            return true;
        }
        boolean[] map = new boolean[256];
        for (int i = 0; i < chas.length; i++) {
            if (map[chas[i]]) {
                return false;
            }
            map[chas[i]] = true;
        }
        return true;
    }

    public static void main(String[] args) {
        char[] arr = new char[]{'a', 'b', 'c'};

        System.out.println(UniqueCharArray1.isUnique(arr));
    }
}
