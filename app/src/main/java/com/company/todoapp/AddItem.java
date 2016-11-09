package com.branwenevans.todoapp;

/**
 * Created by Sameer on 1/11/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.branwenevans.todo.R;


public class AddItem extends AppCompatActivity
{

    public static final String New_item = "New_Word";
    public static final String New_Des = "New_Word";
    private android.support.v7.widget.Toolbar toolbar;
    Context object= this;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.act_toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
    public void AddNew(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        String NewContent = ((EditText) findViewById(R.id.edit_message)).getText().toString();
        String NewDes = ((EditText) findViewById(R.id.descrip)).getText().toString();
       // intent.putExtra(New_Des, NewDes);


            SqliteTableDatabase obj =  new SqliteTableDatabase(object);
            obj.add_info(obj,NewContent,NewDes);
            Toast.makeText(getBaseContext(),"Successfully Added",Toast.LENGTH_SHORT).show();





        intent.putExtra(New_item, NewContent);


        setResult(Activity.RESULT_OK, intent);

        finish();

    }
}
