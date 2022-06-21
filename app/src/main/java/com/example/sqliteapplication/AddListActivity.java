package com.example.sqliteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddListActivity extends AppCompatActivity {

    ImageView back;
    EditText name, price, desc;
    Button save;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        name = findViewById(R.id.inputname);
        price = findViewById(R.id.inputprice);
        desc = findViewById(R.id.inputdesc);

        back = findViewById(R.id.btnback);
        save = findViewById(R.id.btnsave);

        db = new DBHelper(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametxt = name.getText().toString();
                String pricetxt = price.getText().toString();
                String desctxt = desc.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(nametxt, pricetxt, desctxt);

                if (checkinsertdata==true){
                    Toast.makeText(AddListActivity.this, "Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddListActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}