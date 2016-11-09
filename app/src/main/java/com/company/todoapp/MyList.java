package com.branwenevans.todoapp;


/**
 * Created by Sameer on 1/11/16.
 */

public class MyList
{

    private boolean num;
    private String Str;


    public MyList(String Str)
    {
        this.Str = Str;
    }

    public MyList(String Str, boolean num)
    {
        this.Str = Str;
        this.num = num;
    }

    public String getStr()
    {
        return Str;
    }

    public boolean Num_Get()
    {
        return num;
    }

    public void Num()
    {
        this.num = true;
    }

}

