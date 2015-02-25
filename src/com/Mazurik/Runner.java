package com.Mazurik;

/**
 * Created by alekseymazurik on 2/23/15.
 */
public class Runner {
    public static void main(String[] args) {
        long startTimeSequential;
        long msecTimeElapsedSequential;
        long startTimeParallel;
        long msecTimeElapsedParallel;
        int size = 1000;
        int numberOfThreads[] = {2, 3, 4, 6, 8};

        Matrix m = new Matrix(size);
        Matrix n = new Matrix(size);

        startTimeSequential = System.nanoTime();

        m.revertLines(size, 0, size);

        msecTimeElapsedSequential = (System.nanoTime() - startTimeSequential) / 1000000;
        System.out.println("Time taken to sequential: " + msecTimeElapsedSequential);
        System.out.println("==============================");


        for(int i = 0; i < numberOfThreads.length; i++) {

            System.out.println("Number of threads: " + numberOfThreads[i]);
            startTimeParallel = System.nanoTime();
            n.revertMatrixParallel(numberOfThreads[i]);
            msecTimeElapsedParallel = (System.nanoTime() - startTimeParallel) / 1000000;
            System.out.println("Time taken to parallel: " + msecTimeElapsedParallel);
        }

    }
}
