package hello.androidtown.org.lab12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public EditText edit_name;

    public Button btn_print;
    public Button btn_clear;
    public TextView view_print;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }
    public void init(){
        edit_name = (EditText)findViewById(R.id.editText);
        btn_clear = (Button)findViewById(R.id.btn1);
        btn_print = (Button)findViewById(R.id.btn2);
        view_print = (TextView)findViewById(R.id.textView);
    }
    // This method is clearing text
    public void clearClicked(View v){
        edit_name.setText("");
        view_print.setText("contents");
    }
    // This method is printing text
    public void printClicked(View v){
        String text = "";
        text = edit_name.getText().toString();

        if(!TextUtils.isEmpty(edit_name.getText().toString()))
            view_print.setText(edit_name.getText().toString());
        edit_name.setText(text);
    }
}
