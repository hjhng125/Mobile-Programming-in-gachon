package hello.androidtown.org.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public Button btn_frag1;
    public Button btn_frag2;

    public  fragment1 frag1;
    public  fragment2 frag2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListener();
    }
    public void init(){
        btn_frag1 = (Button)findViewById(R.id.btn1);
        btn_frag2 = (Button)findViewById(R.id.btn2);

        frag1 = new fragment1();
        frag2 = new fragment2();
    }
    private void setListener(){
        btn_frag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_for_fragment,
                        frag1).addToBackStack(null).commit();
            }
        });
        btn_frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_for_fragment,
                        frag2).addToBackStack(null).commit();
            }
        });
    }
}
