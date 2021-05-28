package com.wayneyong.flag_quiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDAO {

    //1-get 10 questions from database, except for correct ans, need to get 3 wrong ans
    //keep data pull from db in an arraylist
    public ArrayList<FlagsModel> getRandomTenQuestion(FlagsDatabase fd) {

        ArrayList<FlagsModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = fd.getWritableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM flagquizgametable ORDER BY RANDOM() LIMIT 10", null); //read line by line

        int flagIdIndex = cursor.getColumnIndex("flag_id");
        int flagNameIndex = cursor.getColumnIndex("flag_name");
        int flagImageIndex = cursor.getColumnIndex("flag_image");

        //cursor will read first cell in db until the last cell of db
        while (cursor.moveToNext()) {

            FlagsModel model = new FlagsModel(cursor.getInt(flagIdIndex)
                    , cursor.getString(flagNameIndex)
                    , cursor.getString(flagImageIndex));
            modelArrayList.add(model);
        }
        return modelArrayList;
    }

    public ArrayList<FlagsModel> getRandomThreeOptions(FlagsDatabase fd, int flag_id) {

        ArrayList<FlagsModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = fd.getWritableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM flagquizgametable WHERE flag_id != " + flag_id + "  ORDER BY RANDOM () LIMIT 3", null); //read line by line

        int flagIdIndex = cursor.getColumnIndex("flag_id");
        int flagNameIndex = cursor.getColumnIndex("flag_name");
        int flagImageIndex = cursor.getColumnIndex("flag_image");

        //cursor will read first cell in db until the last cell of db
        while (cursor.moveToNext()) {

            FlagsModel model = new FlagsModel(cursor.getInt(flagIdIndex)
                    , cursor.getString(flagNameIndex)
                    , cursor.getString(flagImageIndex));
            modelArrayList.add(model);
        }
        return modelArrayList;
    }
}
