package com.Mazurik;

import java.util.*;

/**
 * Created by alekseymazurik on 2/24/15.
 */

public class Matrix {

    int size;
    int[][] matr;

    Matrix(int n) {
        this.size = n;
        this.matr = new int[n][n];
        this.createMatrix();
    }

    public void createMatrix(){
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matr[i][j] = rand.nextInt(100);
            }
        }
    }

    public void revertMatrixParallel(int numberOfThreads) {
        List<Thread> threads = new ArrayList<Thread>();

        int generalAmount = this.size / 2;

        int mod = generalAmount % numberOfThreads;
        int rangeSize = (generalAmount - mod) / numberOfThreads;
        int start = 0;
        int end = 0;

        for(int i = 0; i < numberOfThreads; i++) {

            start = (i == 0) ? start : end;
            end = start + rangeSize;

            if (i == numberOfThreads - 1) {
                end += mod;
            }

            threads.add(new Thread(new CreateLineTask(this.size, start, end)));
        }

        for(Thread t: threads) {
            t.start();
        }

        for(Thread t: threads) {
            try {
                t.join();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class CreateLineTask implements Runnable {
        int start;
        int end;
        int length;

        CreateLineTask(int length, int start, int end) {
            this.start = start;
            this.end = end;
            this.length = length;
        }

        @Override
        public void run() {
            revertLines(length, start, end);
        }

    }

    public void revertMatrix(int length){
        for (int i = 0; i < length / 2; i++) {
            for (int j = 0; j < length; j++) {
                int tmp = matr[i][j];
                matr[i][j] = matr[length-i-1][j];
                matr[length-i-1][j] = tmp;
            }
        }
    }


    public void revertLines(int length, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < length; j++) {
                int tmp = matr[i][j];
                matr[i][j] = matr[length-i-1][j];
                matr[length-i-1][j] = tmp;
            }
            int tmp2 = dumb();
        }
    }

    public void showMatrix(){
        if (this.matr.length > 0) {
            for (int i = 0; i < this.size; i++) {
                System.out.print(i + " : ");
                for (int j = 0; j < this.size; j++) {
                    System.out.print(this.matr[i][j] + " ");
                }
                System.out.print("\n");
            }
        }
    }

    private int dumb(){
        Random random = new Random();

        int count = 100;
        double helper = 1.1;

        while (count > 50) {
            int max = 49998 + Math.abs(random.nextInt()) % 2;
            for (int i = 2; i < max; ++i) {
                helper *= i;
                helper /= (i-1);
            }
            count--;
        }

        return (int)(helper * 0.0 + (double )count * 0);
    }

}
