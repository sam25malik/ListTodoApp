package com.branwenevans.todoapp;

/**
 * Created by Sameer on 1/11/16.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.branwenevans.todo.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;



public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{

    private List<MyList> table;
    private List<MyList> tabledone = new ArrayList<>();


    private boolean showtablecont = true;

    Context ctx;

    public MyAdapter(List<MyList> table,Context ctx)
    {
        this.table = table;
        this.ctx=ctx;
        for (MyList item : table)
        {
            if (item.Num_Get())
            {
                tabledone.add(item);
            }
        }



    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view,ctx);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
        final MyList listItem = table.get(position);
        if (!listItem.Num_Get() || showtablecont)
        {
            holder.textView.setText(listItem.getStr());
            holder.textView.getPaint().setStrikeThruText(listItem.Num_Get());
            holder.doneLink.setVisibility(listItem.Num_Get() ? View.GONE : View.VISIBLE);

        }

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,ClickItem.class);
                //intent.putExtra("title_id",tabledone.getStr());
               intent.putExtra("text",listItem.getStr());
                intent.putExtra("position",position);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);

                Toast.makeText(ctx,listItem.getStr()+" Clicked",Toast.LENGTH_SHORT).show();
            }
        });


    }




    @Override
    public int getItemCount()
    {
        return table.size();
    }
    public void toggleShowCompletedTasks()
    {
        showtablecont = !showtablecont;

        if (tabledone.size() > 0)
        {
            if (showtablecont)
            {
                table.addAll(tabledone);
                notifyItemRangeInserted(table.size() - tabledone.size(), table.size() - 1);
            }
            else
            {
                for (MyList doneItem : tabledone)
                {
                    int index = table.indexOf(doneItem);
                    table.remove(index);
                    notifyItemRemoved(index);
                }
            }
        }
    }

    public void event_done(int position)
    {
        table.get(position).Num();
        if (!showtablecont)
        {
            tabledone.add(table.remove(position));
            notifyItemRemoved(position);
        }
        else
        {
            tabledone.add(table.get(position));
            notifyItemChanged(position);
        }
    }

    public void event_del(int position)
    {
        tabledone.remove(table.remove(position));
        notifyItemRemoved(position);
    }

    public void event_add(MyList listItem)
    {
        table.add(listItem);
        notifyItemInserted(table.size() - 1);
    }

    public Collection<MyList> event_get()
    {
        LinkedHashSet<MyList> allItems = new LinkedHashSet<MyList>(table);
        allItems.addAll(tabledone);
        return allItems;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder //implements View.OnClickListener
    {

        private final TextView textView;

        private final ImageButton doneLink;
        ArrayList<MyList> tabledone= new ArrayList<MyList>();
        Context ctx;

 //public ViewHolder(View itemView,Context ctx,ArrayList<MyList> tabledone)
        public ViewHolder(View itemView,Context ctx)

        {
            super(itemView);
           // this.tabledone=tabledone;
            this.ctx=ctx;

         //  itemView.setOnClickListener(this);
            textView = (TextView) itemView.findViewById(R.id.item_text);
            doneLink = (ImageButton) itemView.findViewById(R.id.item_done);
        }

        /*@Override
        public void onClick(View v)
        {
           int position=getAdapterPosition();
            //MyList tabledone = this.tabledone.get(position);

            Toast.makeText(ctx, "" + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this.ctx,ClickItem.class);
            //intent.putExtra("title_id",tabledone.getStr());
            intent.putExtra("pos",position);
           // intent.putExtra("postId" , textView.getText().toString());
            this.ctx.startActivity(intent);


        }*/

    }
}
