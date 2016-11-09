package com.branwenevans.todoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.branwenevans.todo.R;

/**
 * Created by Sameer on 01/11/16.
 */
public class ClickItem extends AppCompatActivity {
    TextView tx_title;
   // TextView textViewMessage;
    private android.support.v7.widget.Toolbar toolbar;
    String ans;
    String cont;
    ViewPager viewPager;
    ScrollAdapter adapter;
    int cur_pos;
    int mCurrentFragmentPosition;

    Context l = this;


    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_itemclick);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        adapter = new ScrollAdapter(this);
        viewPager.setAdapter(adapter);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.act_toolbar);
        setSupportActionBar(toolbar);


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        tx_title = (TextView)findViewById(R.id.des_item);
       // textViewMessage=(TextView)findViewById(R.id.realdes);
      //tx_title.setText("title_id : " +getIntent().getStringExtra("title_id"));
        //tx_title.setText("title_id : " +getIntent().getExtras().getInt("pos"));
        //textViewMessage=(TextView)findViewById(R.id.realdes);
       // textViewMessage.setText(getIntent().getExtras().getString("postId"));

      // tx_title.setText(getIntent().getStringExtra("text"));
        ans = getIntent().getStringExtra("text");
        tx_title.setText("Details");
        cur_pos= getIntent().getExtras().getInt("position");


        SqliteTableDatabase n = new SqliteTableDatabase(l);
        Cursor l2 = n.login_info(n);
        l2.moveToFirst();
        boolean stat = false;
        String N_Stat = "";

        do {
            if(ans.equals(l2.getString(0)))
            {
                stat = true;
               // textViewMessage.setText(ans);

                N_Stat = l2.getString(0);
              //  textViewMessage.setText(l2.getString(1));
                cont = l2.getString(1);

                // textViewMessage.setText(cont);
                tx_title.setText(cont);
                //textViewMessage.setText(ans);


            }
        }while (l2.moveToNext());
      /* if(stat)
        {
            Toast.makeText(getBaseContext(),"Details of Title: "+ans,Toast.LENGTH_SHORT).show();
//            textViewMessage.setText(l2.getString(1));

        }
        else
        {
            Toast.makeText(getBaseContext(),"Login Failed",Toast.LENGTH_SHORT).show();
           // textViewMessage.setText(l2.getString(1));

        }*/

        SqliteTableDatabase n2 = new SqliteTableDatabase(l);
        Cursor l3 = n2.login_info(n2);
        l3.moveToFirst();

        String[] array = new String[l3.getCount()];
        int i = 0;
        while(l3.moveToNext()){
            String uname = l3.getString(1);
            array[i] = uname;
            i++;
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


    public class ScrollAdapter extends PagerAdapter {

        private int[] table_res = {R.drawable.v1,R.drawable.v2,R.drawable.v3};
        //String[] array = new String[100];

        int k=0;
        private Context ctx;
        private LayoutInflater layoutInflater;

        int pos;

        public ScrollAdapter(Context ctx)
        {
            this.ctx=ctx;
            //  layoutInflater = LayoutInflater.from(ctx);


        }


        @Override
        public int getCount()
        {
            //return table_res.length;
            //return array.length;

            return 100;


        }
        @Override
        public boolean isViewFromObject(View view, Object o)
        {
            return (view==(LinearLayout)o);


        }
        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            layoutInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View item_view = layoutInflater.inflate(R.layout.scroll_layout,container,false);
            //ImageView textview = (ImageView) item_view.findViewById(R.id.scroll_text);
            // textview.setImageResource(table_res[position]);

            TextView textview = (TextView) item_view.findViewById(R.id.scroll_text);

            SqliteTableDatabase n2 = new SqliteTableDatabase(ctx);
            Cursor l3 = n2.login_info(n2);
            l3.moveToFirst();
            String cont;
            cont = l3.getString(1);
            //textview.setText(l3.getString(1));
            //l3.moveToNext();
            pos= getIntent().getExtras().getInt("position");

            k=1;
            String[] array = new String[100];

            array[0]=l3.getString(1);
            //array[1]=l3.getString(1);

            int i = 1;
            while(l3.moveToNext()){
                String uname = l3.getString(1);
                array[i] = uname;
                i++;


            }
            //array[0]=array[pos];

            /* if(pos==1) {
                array[1]=l3.getString(1);

            }else
            {
                array[0] = array[pos];

            }*/

            //viewPager.setCurrentItem(pos-1);
            textview.setText(array[(position)%i]);

            //textview2.setText(table_res[position]); //addd set text description
            container.addView(item_view);
            return item_view;




        }

        @Override
        public void destroyItem(ViewGroup container,int position,Object object) {
            container.removeView((LinearLayout) object);

        }

    }
/*<android.support.v4.view.ViewPager
        android:id="@+id/view_pager"
        android:layout_height="match_parent"
        android:layout_width="match_parent">

</android.support.v4.view.ViewPager>*/


}
