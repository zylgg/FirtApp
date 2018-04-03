package com.example.zyl.firtapp;

/**
 * Created by zyl on 2018/3/19.
 */

public class test {
    public synchronized  void aaa() throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            Thread.sleep(100);
            System.out.println("aaa_" + i);
        }
    }

    public   void bbb() throws InterruptedException {
        for (int i = 1; i <= 4; i++) {
            Thread.sleep(100);
            System.out.println("bbb_" + i);
        }
    }

    private void round(){
        System.out.print(Math.round(-11.4));
    }


    public static void main(String[] args) {
        final test t = new test();
//        t.round();
        new Thread() {
            @Override
            public void run() {
                try {
                    t.aaa();
                } catch (InterruptedException e) {
                 e.printStackTrace();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                try {
                    t.bbb();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
