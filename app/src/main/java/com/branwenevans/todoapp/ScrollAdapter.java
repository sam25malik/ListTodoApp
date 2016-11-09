package com.branwenevans.todoapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.branwenevans.todo.R;

/**
 * Created by Sameer on 02/11/16.
 */
/*
public class ScrollAdapter extends PagerAdapter {

   private int[] table_res = {R.drawable.v1,R.drawable.v2,R.drawable.v3};
    //String[] array = new String[100];

    int k=0;
    private Context ctx;
    private LayoutInflater layoutInflater;

    int pos;
    String strtext = getArguments().getString("edttext");

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
    public Object instantiateItem(ViewGroup container,int position)
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

        k=1;
        String[] array = new String[100];
        int i = 1;

        array[0]=l3.getString(1);
        while(l3.moveToNext()){
            String uname = l3.getString(1);
            array[i] = uname;
            i++;


        }

        textview.setText(array[position]);
        k++;

        //textview2.setText(table_res[position]); //addd set text description
        container.addView(item_view);
        return item_view;




    }

    @Override
    public void destroyItem(ViewGroup container,int position,Object object)
    {
        container.removeView((LinearLayout)object);

    }


}
/*<android.support.v4.view.ViewPager
        android:id="@+id/view_pager"
        android:layout_height="match_parent"
        android:layout_width="match_parent">

</android.support.v4.view.ViewPager>*/

