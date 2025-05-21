package queue;

import model.Job;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class JobQueue {
    private static final BlockingQueue<Job> queue = new LinkedBlockingQueue<>();

    public static void add(Job job) {
        queue.offer(job);
    }

    public static Job take() throws InterruptedException {
        return queue.take();
    }
}