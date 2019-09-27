package com.edu.princeton.cs.learn;

import com.edu.princeton.cs.algs4.ST;
import com.edu.princeton.cs.algs4.StdDraw;
import com.edu.princeton.cs.algs4.StdOut;
import com.edu.princeton.cs.algs4.StdRandom;

/**
 * Created by zhoutaotao on 2018/9/18.
 */
public class VisualAccumulator {

    private double total;
    private int N;

    public VisualAccumulator(int trials,double max){
        StdDraw.setXscale(0,trials);
        StdDraw.setYscale(0,max);
        StdDraw.setPenRadius(.005);
    }

    public void addDataValue(double val){
        N++;

        total+=val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N,val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N,total/N);
    }

    public static void main(String[] args) {
        VisualAccumulator a = new VisualAccumulator(10000,1.0);
        for(int i =0;i<10000;i++){
            a.addDataValue(StdRandom.random());
        }
        StdOut.println(a);

    }
}


