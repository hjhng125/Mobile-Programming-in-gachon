package hello.androidtown.org.lab3_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    RadioButton radiomale, radiofemale;
    CheckBox checkSMS, checkemail;
    Button btn;
    Bundle ii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.editText);
        radiomale = (RadioButton)findViewById(R.id.male);
        radiofemale = (RadioButton)findViewById(R.id.female);
        checkSMS = (CheckBox)findViewById(R.id.sms);
        checkemail = (CheckBox)findViewById(R.id.email);
        btn = (Button)findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                String gender = "";
                String receive = "";

                if(radiomale.isChecked())
                    gender = " " + radiomale.getText().toString();
                else
                    gender = " " + radiomale.getText().toString();
                if(checkSMS.isChecked())
                    receive += " " + checkSMS.getText().toString();
                if(checkemail.isChecked())
                    receive += " " + checkemail.getText().toString();
                intent.putExtra("name",editText.getText().toString());
                intent.putExtra("gender", gender);
                intent.putExtra("receive",receive);

                startActivity(intent);
                finish();
            }
        });
    }
}
