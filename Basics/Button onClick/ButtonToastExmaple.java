package yuwei.documentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ButtonToastExmaple extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_toast_example);
    }

    public void buttonOnClick(View view) {
        Toast.makeText(this, "Toast!", Toast.LENGTH_LONG).show();
    }
}
