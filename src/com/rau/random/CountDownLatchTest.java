package com.rau.random;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        Thread t1= new DevTeam(countDownLatch,"DevTask");
        Thread t2= new DevTeam(countDownLatch,"DevTask");
        t1.start();
        t2.start();

        countDownLatch.await();

        Thread t3= new QaTeam(countDownLatch,"QaTeam");
        t3.start();

        System.out.println("Main ended");
    }

    static class DevTeam extends Thread {
        CountDownLatch countDownLatch;

        DevTeam(CountDownLatch countDownLatch, String name) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("DevTeam run started for thread:" + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("DevTeam run ended for thread:" + Thread.currentThread().getName());
            countDownLatch.countDown();
        }
    }
    static class QaTeam extends Thread {
        CountDownLatch countDownLatch;

        QaTeam(CountDownLatch countDownLatch, String name) {
            super(name);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            System.out.println("Dev task completed.So, QaTeam run started for thread:" + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("QaTeam run ended for thread:" + Thread.currentThread().getName());
            countDownLatch.countDown();
        }
    }
}
