package yuwei.documentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.InterruptedIOException;

public class ThreadExample2 extends AppCompatActivity {

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_example2);
        thread3.start();
    }

    Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            while(flag){
                Log.d("thread3", "Running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.d("thread3", "Stopped");
        }
    });

    public void t3StopOnClick(View view) {
        flag = false;
    }
}
