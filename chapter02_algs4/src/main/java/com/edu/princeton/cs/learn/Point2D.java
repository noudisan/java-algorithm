package com.edu.princeton.cs.learn;

import com.edu.princeton.cs.algs4.StdDraw;
import com.edu.princeton.cs.algs4.StdIn;
import com.edu.princeton.cs.algs4.StdRandom;

/**
 * Created by zhoutaotao on 2018/9/19.
 */
public class Point2D {

    public static void main(String[] args) {
        int readInt = StdIn.readInt();
        System.out.println(readInt);

        StdDraw.setXscale(0,readInt);
        StdDraw.setYscale(0,readInt);
        StdDraw.setPenRadius(.005);

        for(int i =0;i<readInt;i++){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.point(i++, StdRandom.random()*readInt);
        }

        StdDraw.line(10,20,30,40);
    }
}
