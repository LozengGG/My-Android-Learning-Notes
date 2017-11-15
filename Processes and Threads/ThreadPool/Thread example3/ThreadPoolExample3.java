package yuwei.documentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample3 extends AppCompatActivity {

    private ExecutorService mThreadPool = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_example3);

        mThreadPool.execute(new Task1());
        mThreadPool.execute(new Task2());

        try {
            mThreadPool.shutdown();
            if(!mThreadPool.awaitTermination(8, TimeUnit.SECONDS)){
                Log.d("mThreadPool", "Await time out, shutdownNow");
                mThreadPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            mThreadPool.shutdownNow();
        }
    }

    private class Task1 implements Runnable {

        @Override
        public void run() {
            for(int i = 1; i <= 5; i++) {
                Log.d("task1", i + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    break;
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
                    Log.d("task2", "break");
                    //e.printStackTrace();
                    break;
                }
            }
        }
    }
}
