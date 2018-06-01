package com.example.hjh.lab6_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText stNum, name;
    Button addBtn, deleteBtn;
    ListView listView;
    String[] list;
    SQLiteDatabase sqLiteDatabase;
    MySQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stNum = findViewById(R.id.stNum);
        name = findViewById(R.id.name);
        addBtn = findViewById(R.id.addBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        listView = findViewById(R.id.listView);
        helper = new MySQLiteOpenHelper(MainActivity.this, "student.db", null, 1);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(stNum.getText()))
                    Toast.makeText(getApplicationContext(), "모든 항목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                else {
                    insert(name.getText().toString(), stNum.getText().toString());
                    invalidate();
                }
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText())) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    delete(name.getText().toString());
                    invalidate();
                }
            }
        });
        invalidate();
    }

    private void invalidate() {
        select();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    public void insert(String name, String stNum) {
        sqLiteDatabase = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("stNum", stNum);
        sqLiteDatabase.insert("student", null, values);
    }

    public void delete(String name) {
        sqLiteDatabase = helper.getWritableDatabase();
        sqLiteDatabase.delete("student", "name=?", new String[]{name});
    }

    public void select() {
        sqLiteDatabase = helper.getReadableDatabase();
        Cursor c = sqLiteDatabase.query("student", null, null, null, null, null, null);

        list = new String[c.getCount()];
        int count = 0;
        while (c.moveToNext()) {
            list[count] = c.getString(c.getColumnIndex("name")) + " " + c.getString(c.getColumnIndex("stNum"));
            count++;
        }
        c.close();
    }

    public class MySQLiteOpenHelper extends SQLiteOpenHelper {
        public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        public void onCreate(SQLiteDatabase db) {
            String sql = "create table student (name text, stNum text);";
            db.execSQL(sql);
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "drop table if exists student";
            db.execSQL(sql);
            onCreate(db);
        }
    }
}
