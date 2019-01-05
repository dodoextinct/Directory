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


    //initialising the variables
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
        ref = database.getReference("info");//getting the database reference
        list = new ArrayList<>();//setting up list
        
        //setting up the adapate for the list
        adapter = new ArrayAdapter<String>(this, R.layout.info, R.id.name1, list);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                //sending data to different activity for display
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
                    
                    //getting all the data
                    name = ds.child("name").getValue().toString();
                    mob = ds.child("mob").getValue().toString();
                    loc = ds.child("loc").getValue().toString();
                    dob = ds.child("dob").getValue().toString();
                    bg = ds.child("bg").getValue().toString();
                    id = ds.child("id").getValue().toString();
                    list.add(name);//adding to list for the display
                    
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
        //switch back to the activitymain
        Intent intent = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(intent);
    }
}
