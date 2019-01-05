package com.example.yashkrishan.directory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    DatabaseReference ref;
    FirebaseDatabase database;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    Person person;
    TextView textView;
    String name;
    String mob;
    String loc;
    String dob;
    String bg;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = (TextView)findViewById(R.id.name1);
        person = new Person();
        listView = (ListView)findViewById(R.id.list);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("info");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.info, R.id.name1, list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent in = new Intent(Main2Activity.this, Main3Activity.class);
                in.putExtra("Name", name);
                in.putExtra("Mob", mob);
                in.putExtra("Loc", loc);
                in.putExtra("Dob", dob);
                in.putExtra("Bg", bg);
                in.putExtra("Id", id);
                startActivity(in);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    name = ds.child("name").getValue().toString();
                    mob = ds.child("mob").getValue().toString();
                    loc = ds.child("loc").getValue().toString();
                    dob = ds.child("dob").getValue().toString();
                    bg = ds.child("bg").getValue().toString();
                    id = ds.child("id").getValue().toString();
                    list.add(name);
                    Person person = new Person(bg, dob, loc, id, mob, name);
                }
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
