package yuwei.documentdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HandlerExample extends AppCompatActivity {

    private Handler handler;
    private enum HANDLE_MESSAGE {
        UPDATE_TEXTVIEW,   // update textview text
        UPDATE_IMAGEVIEW   // just for example
    }
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_example);
        textView = (TextView) findViewById(R.id.txv_handler_exp);
        setHandler();
    }

    private void setHandler() {
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == HANDLE_MESSAGE.UPDATE_TEXTVIEW.ordinal()){
                    String text = Integer.toString(msg.getData().getInt("count"));
                    textView.setText(text);
                }
            }
        };
    }

    public void btnHandlerExpOnClick(View view) {
        Log.d("Handler example", "Start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    Message msg = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putInt("count", i);
                    msg.setData(bundle);
                    msg.what = HANDLE_MESSAGE.UPDATE_TEXTVIEW.ordinal();
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("Handler example", "Running: " + i);
                }
            }
        }).start();
    }
}
