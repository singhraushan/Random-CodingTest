package com.rau.random;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cb = new CyclicBarrier(2);
        new CarJointPoint(cb,"Amit").start();
        new CarJointPoint(cb,"Raushan").start();
        new CarJointPoint(cb,"Chandan").start();
        new CarJointPoint(cb,"Venkat").start();

        new CarJointPoint(cb,"Ankit").start();
        new CarJointPoint(cb,"Suman").start();
        new CarJointPoint(cb,"Rajan").start();
        new CarJointPoint(cb,"Ram").start();
        //new CarJointPoint(cb,"Suraj").start();

        System.out.println("Main ended.");
    }
    static class CarJointPoint extends Thread {
        CyclicBarrier cyclicBarrier;

        CarJointPoint(CyclicBarrier cyclicBarrier, String name) {
            super(name);
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("Person reached at joint point is:" + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
                int count = cyclicBarrier.await();
                if(count==0){
                    System.out.println(cyclicBarrier.getParties()+" person has reached at joint point.");
                }
            }catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
