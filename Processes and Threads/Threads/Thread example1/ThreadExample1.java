package yuwei.documentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ThreadExample1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_example1);
        Log.d("tag1", "message");

    }

    public void btn1OnClick(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("Button1", "Start running for 5 second");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("Button1", "Finished");
            }
        }).start();
    }


    public class task implements Runnable{

        @Override
        public void run() {
            Log.d("Button2", "Start");
            try {
                for(int i = 0; i < 3; i++){
                    Thread.sleep(1000);
                    Log.d("Button2", Integer.toString(i+1));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("Button2", "finished");
        }
    }

    public void btn2OnClick(View view) {
        new Thread(new task()).start();
    }
}
