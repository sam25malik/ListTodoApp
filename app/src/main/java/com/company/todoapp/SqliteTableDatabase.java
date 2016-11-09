package com.branwenevans.todoapp;

/**
 * Created by Sameer on 03/11/16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Sameer on 29/09/16.
 */
public class SqliteTableDatabase extends SQLiteOpenHelper {


    public static final int version =1;
    public String query = "CREATE TABLE " + SqliteTable.SqliteTableInfo.Name_Of_Table + "(" + SqliteTable.SqliteTableInfo.Student_Name + " TEXT," + SqliteTable.SqliteTableInfo.Student_Pass+ " TEXT)";

    //1.public String query = "CREATE TABLE "+SqliteTable.SqliteTableInfo.Name_Of_Table+"("+SqliteTable.SqliteTableInfo.Student_Name+" TEXT,"+SqliteTable.SqliteTableInfo.Student_Roll+" TEXT,"+SqliteTable.SqliteTableInfo.Student_Sem+" TEXT,"+SqliteTable.SqliteTableInfo.Student_Pass+" TEXT);";
    //public String query = "CREATE TABLE "+SqliteTable.SqliteTableInfo.Name_Of_Table+"("+SqliteTable.SqliteTableInfo.Student_Name+" TEXT,"+SqliteTable.SqliteTableInfo.Student_Roll+" TEXT,"+SqliteTable.SqliteTableInfo.Student_Sem+" TEXT,"+SqliteTable.SqliteTableInfo.Student_Pass+" TEXT,"+")";

    //public String query = "CREATE TABLE " + SqliteTable.SqliteTableInfo.Name_Of_Table + "(" + SqliteTable.SqliteTableInfo.Student_Name + " TEXT," + SqliteTable.SqliteTableInfo.Student_Roll + " TEXT," + SqliteTable.SqliteTableInfo.Student_Sem + " TEXT," + SqliteTable.SqliteTableInfo.Student_Pass + " TEXT," + ")";

    public SqliteTableDatabase(Context context)
    {
        super(context,SqliteTable.SqliteTableInfo.Database_Name,null,version);
        Log.d("Database Working","Sqlite Database Created");


    }

    @Override
    public void onCreate(SQLiteDatabase student)
    {
        student.execSQL(query);
        Log.d("Database Working","Sqlite Table Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase agr0,int arg1,int arg2)
    {

    }

    public void add_info(SqliteTableDatabase p,String name,String pass)
    {
        SQLiteDatabase Write= p.getWritableDatabase();
        ContentValues write_new = new ContentValues();
        write_new.put(SqliteTable.SqliteTableInfo.Student_Name,name);
        write_new.put(SqliteTable.SqliteTableInfo.Student_Pass,pass);
        long in = Write.insert(SqliteTable.SqliteTableInfo.Name_Of_Table,null,write_new);
        Log.d("Database Working","Row Created");

    }

    public Cursor login_info(SqliteTableDatabase p)
    {
        SQLiteDatabase Write = p.getReadableDatabase();
        String[] col = {SqliteTable.SqliteTableInfo.Student_Name, SqliteTable.SqliteTableInfo.Student_Pass};
        Cursor yi = Write.query(SqliteTable.SqliteTableInfo.Name_Of_Table,col,null,null,null,null,null);
        return yi;


    }

    public Cursor login_pass(SqliteTableDatabase p,String name)
    {
        SQLiteDatabase Write = p.getReadableDatabase();
        String l_sel = SqliteTable.SqliteTableInfo.Student_Name +" LIKE ?";
        String col[] = {SqliteTable.SqliteTableInfo.Student_Pass};
        String args[] = {name};
        Cursor yi = Write.query(SqliteTable.SqliteTableInfo.Name_Of_Table,col,null,null,null,null,null);
        return yi;


    }

    public void user_delete(SqliteTableDatabase p,String name,String pass)
    {
        String l_sel = SqliteTable.SqliteTableInfo.Student_Name+ " LIKE ? AND "+SqliteTable.SqliteTableInfo.Student_Pass +" LIKE ?";
        String col[] = {SqliteTable.SqliteTableInfo.Student_Pass};
        String args[] = {name,pass};
        SQLiteDatabase write = p.getReadableDatabase();
        write.delete(SqliteTable.SqliteTableInfo.Name_Of_Table,l_sel,args);




    }



}
