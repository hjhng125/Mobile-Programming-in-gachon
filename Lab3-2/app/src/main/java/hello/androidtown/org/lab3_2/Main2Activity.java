package hello.androidtown.org.lab3_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    TextView resultName, resultGender, resultReceive;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        resultName = (TextView)findViewById(R.id.resultName);
        resultGender = (TextView)findViewById(R.id.resultGender);
        resultReceive = (TextView)findViewById(R.id.resultReceive);
        Intent intent = getIntent();

        resultName.setText(intent.getStringExtra("name"));
        resultGender.setText(intent.getStringExtra("gender"));
        resultReceive.setText(intent.getStringExtra("receive"));

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
