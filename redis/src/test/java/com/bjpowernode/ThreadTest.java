package com.bjpowernode;

/**
 * 孙韫杰
 * 2021/8/8 0008
 */
public class ThreadTest {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new MyThread().start();
        }
    }

    static class MyThread extends Thread {
        volatile public static int count;
        public static void addCount() {
            for (int i = 0; i < 1000; i++) {
                //count++不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }
        /*public synchronized static void addCount() {
            for (int i = 0; i < 1000; i++) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() + " count=" + count);
        }*/

        @Override
        public void run() {
            addCount();
        }
    }
}
