package com.example.yashkrishan.directory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    TextView name;
    TextView mob;
    TextView loc;
    TextView dob;
    TextView bg;
    TextView id;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent i = getIntent();
        name = (TextView)findViewById(R.id.name2);
        mob = (TextView)findViewById(R.id.mob2);
        loc = (TextView)findViewById(R.id.loc2);
        dob = (TextView)findViewById(R.id.dob2);
        bg = (TextView)findViewById(R.id.bg2);
        id = (TextView)findViewById(R.id.id2);
        name.setText(i.getStringExtra("Name"));
        mob.setText(i.getStringExtra("Mob"));
        loc.setText(i.getStringExtra("Loc"));
        dob.setText(i.getStringExtra("Dob"));
        bg.setText(i.getStringExtra("Bg"));
        id.setText(i.getStringExtra("Id"));
        btn = (Button)findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Main3Activity.this, Main2Activity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
