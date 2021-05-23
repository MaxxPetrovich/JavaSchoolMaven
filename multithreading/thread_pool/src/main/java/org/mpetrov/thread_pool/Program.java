package org.mpetrov.thread_pool;

public class Program {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(7);

        for (int i = 0; i < 500; i++) {
            Task task = new Task(i);
            pool.execute(task);
        }
    }
}
