package com.example.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name, contact, nim;
    Button insert, update, delete, view, edit;
    DBHelper db;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        nim = findViewById(R.id.nim);

        insert = findViewById(R.id.btninsert);
        update = findViewById(R.id.btnupdate);
        delete = findViewById(R.id.btndelete);
        view = findViewById(R.id.btnview);
        edit = findViewById(R.id.btnedit);

        rv = findViewById(R.id.rv);

        db = new DBHelper(this);

       //showItem();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = contact.getText().toString();
                String nimtxt = nim.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(nametxt, contacttxt, nimtxt);

                if (checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String contacttxt = contact.getText().toString();
                String nimtxt = nim.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(nametxt, contacttxt, nimtxt);

                if (checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Update Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Update Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();

                Boolean checkdeletedata = db.deleteuserdata(nametxt);

                if (checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Delete Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Delete Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.viewuserdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                ArrayList<Model> arrayList = new ArrayList<>();
                while (res.moveToNext()){
                    buffer.append("Name: "+res.getString(0)+"\n");
                    buffer.append("Contact: "+res.getString(1)+"\n");
                    buffer.append("Nim: "+res.getString(2)+"\n");
                    Model model = new Model (
                            ""+res.getString(res.getColumnIndexOrThrow("")),
                            ""+res.getString(res.getColumnIndexOrThrow("")),
                            ""+res.getString(res.getColumnIndexOrThrow("desc"))
                    );
                    arrayList.add(model);

                }
//                Adapter adapter = new Adapter(MainActivity.this, arrayList);
//                rv.setAdapter(adapter);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("List user");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddListActivity.class);
                startActivity(intent);
            }
        });
    }
//    private void showItem(){
//        Adapter adapter = new Adapter(MainActivity.this, db.getAllData("price"));
//        rv.setAdapter(adapter);
//    }
//
//    protected void onResume(){
//        super.onResume();
//        showItem();
//    }
}