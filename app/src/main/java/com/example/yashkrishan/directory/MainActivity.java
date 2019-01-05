package com.example.yashkrishan.directory;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText mob;
    EditText dob;
    EditText loc;
    EditText bg;
    EditText id;
    public static DatabaseReference root;
    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dob = (EditText)findViewById(R.id.date);
        name= (EditText)findViewById(R.id.name);
        mob = (EditText)findViewById(R.id.mob);
        loc = (EditText)findViewById(R.id.location);
        id = (EditText)findViewById(R.id.unique);
        bg = (EditText)findViewById(R.id.blood);

        root = FirebaseDatabase.getInstance().getReference("info");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
        FloatingActionButton ref = (FloatingActionButton)findViewById(R.id.fab1);
        ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                mob.setText("");
                loc.setText("");
                bg.setText("");
                id.setText("");
                dob.setText("");
            }
        });

        FloatingActionButton doc = (FloatingActionButton)findViewById(R.id.fab2);
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        dob.setText(sdf.format(myCalendar.getTime()));
    }



    private void send(){
        String n = name.getText().toString();
        String b = bg.getText().toString();
        String d = dob.getText().toString();
        String l = loc.getText().toString();
        String i = id.getText().toString();
        String m = mob.getText().toString();

        if(TextUtils.isEmpty(n) || TextUtils.isEmpty(b) || TextUtils.isEmpty(d) || TextUtils.isEmpty(l) || TextUtils.isEmpty(i)
                || TextUtils.isEmpty(m)){
            Toast.makeText(this, "Details incomplete!!", Toast.LENGTH_SHORT).show();
        }
        else{
            String id = root.push().getKey();
            Person person = new Person(b,d,i,l,m,n);
            root.child(id).setValue(person);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
