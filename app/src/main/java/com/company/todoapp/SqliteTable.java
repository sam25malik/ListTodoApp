package com.branwenevans.todoapp;

import android.content.Intent;
import android.content.res.Configuration;
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
public class SqliteTable {

    public SqliteTable()
    {

    }

    public static abstract class SqliteTableInfo implements BaseColumns
    {
        public static final String Student_Name = "student_name";
        public static final String Student_Pass = "student_pass";

        public  static final String Database_Name = "Student_Info";
        public static final String Name_Of_Table = "Table_Info";


    }

}

