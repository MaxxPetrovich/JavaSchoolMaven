package org.mpetrov.thread_pool;

public class Task implements Runnable {

    private int num;

    public Task(int n) {
        num = n;
    }

    public void run() {
        System.out.println("Task " + num + " is running.");
    }
}
