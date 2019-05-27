package com.m.nachomorati.gabineteselectricoscp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TablerosDBHelper dbHelper = new TablerosDBHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Log.i(this.getClass().toString(), "base de datos preparada.");

        Button btnVerTableros = findViewById(R.id.btn_ir_tableros_activity);
        btnVerTableros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TablerosActivity.class);
                startActivity(intent);
            }
        });

    }
}
