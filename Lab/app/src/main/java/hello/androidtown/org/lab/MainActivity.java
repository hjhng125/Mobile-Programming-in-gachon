package hello.androidtown.org.lab;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mbtn, btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtn = (Button)findViewById(R.id.button);
        registerForContextMenu(mbtn);

        btn = (Button)findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:01055509031"));
                intent.putExtra("SMS Body", "I kill you");
                startActivity(intent);
            }
        });
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Button Menu");
        menu.add( 0, 1, 0, "Red") ;
        menu.add( 0, 2, 0, "Green") ;
        menu.add(0, 3, 0, "Blue") ;
    }

    public boolean onContextItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                mbtn.setTextColor(Color. RED) ;
                return true;
            case 2:
                mbtn.setTextColor(Color. GREEN) ;
                return true;
            case 3:
                mbtn.setTextColor(Color. BLUE) ;
                return true;
        }
        return true;
    }
}