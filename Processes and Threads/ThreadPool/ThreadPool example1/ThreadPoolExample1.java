package yuwei.documentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExample1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_example1);

        ExecutorService mThreadPool = Executors.newSingleThreadExecutor();
        mThreadPool.execute(new Task1());
        mThreadPool.execute(new Task2());
    }

    private class Task1 implements Runnable {

        @Override
        public void run() {
            for(int i = 1; i <= 5; i++) {
                Log.d("task1", i + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private class Task2 implements Runnable {

        @Override
        public void run() {
            for(int i = 1; i <= 5; i++) {
                Log.d("task2", i + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
