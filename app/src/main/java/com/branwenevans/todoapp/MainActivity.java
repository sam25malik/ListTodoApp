package com.branwenevans.todoapp;

/**
 * Created by Sameer on 1/11/16.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.branwenevans.todo.R;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity
{
    private RecyclerView R_V;
    private boolean Status = true;
    public static final int Con_Num = 1;
    private MyAdapter A_D;
    private android.support.v7.widget.Toolbar toolbar;
    ArrayList<String> addArray = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
       toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.act_toolbar);
        setSupportActionBar(toolbar);

        R_V = (RecyclerView) findViewById(R.id.recycler_view);
        R_V.setHasFixedSize(true);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        R_V.setLayoutManager(lm);
        A_D = new MyAdapter(rfile(),this);
        R_V.setAdapter(A_D);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == Con_Num && resultCode == Activity.RESULT_OK)
        {
            String nl = data.getStringExtra(AddItem.New_item);

            if (!nl.isEmpty())
            {
                A_D.event_add(new MyList(nl));

            }
           /* String nd = data.getStringExtra(AddItem.New_Des);
            addArray.add(nd);
            Toast.makeText(getBaseContext(),addArray.get(0).toString(),Toast.LENGTH_LONG).show();
*/
        }
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        writeF();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater ifl = getMenuInflater();
        ifl.inflate(R.menu.menu_list_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_hide_done:
                toggleShowCompletedTasks(item);
                return true;
            case R.id.button:
                Intent add_int = new Intent(this, AddItem.class);
                startActivityForResult(add_int, Con_Num);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void event_add(View view)
    {
        Intent add_int = new Intent(this, AddItem.class);
        startActivityForResult(add_int, Con_Num);
    }


    public void event_done(View view)
    {
        A_D.event_done(R_V.getChildPosition((View) view.getParent()));
    }


    public void event_del(View view)
    {
        A_D.event_del(R_V.getChildPosition((View) view.getParent()));
    }


    private void toggleShowCompletedTasks(MenuItem item)
    {
        Status = !Status;
        item.setIcon(Status ? R.drawable.ic_action_visibility_off : R.drawable.ic_action_visibility);
        A_D.toggleShowCompletedTasks();
    }


    private void writeF()
    {
        FileOutputStream outputStream;
        try
        {
            outputStream = openFileOutput(getString(R.string.file_name), Context.MODE_PRIVATE);
            for (MyList item : A_D.event_get())
            {
                outputStream.write(item.getStr().getBytes());
                outputStream.write(getString(R.string.doneSeparator).getBytes());
                outputStream.write(item.Num_Get() ? getString(R.string.done).getBytes() : getString(R.string.todo).getBytes());
                outputStream.write('\n');
            }
            outputStream.close();
        }
        catch (IOException e)
        {
            //TODO: handle
        }
    }


    private ArrayList<MyList> rfile()
    {
        ArrayList<MyList> lines = new ArrayList<>();
        String line;
        try
        {
            FileReader reader = new FileReader(gFP());
            BufferedReader bufferedReader = new BufferedReader(reader);

            while ((line = bufferedReader.readLine()) != null)
            {
                int separator = line.lastIndexOf(getString(R.string.doneSeparator));
                String done = line.substring(separator + 1);
                lines.add(new MyList(line.substring(0, separator), getString(R.string.done).equals(done)));
            }
        }
        catch (IOException e)
        {
            //TODO: handle
        }
        return lines;
    }


    private String gFP()
    {
        return getBaseContext().getFilesDir() + "/" + getString(R.string.file_name);
    }

}
