package my.fadzlirazali.sqlite_crud_training.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import my.fadzlirazali.sqlite_crud_training.model.Student;

/**
 * Created by agmostudio on 12/27/15.
 */
public class TableControllerStudent extends DatabaseHandler {

    public SQLiteDatabase db;

    public TableControllerStudent(Context context) {
        super(context);
        db= this.getWritableDatabase();
    }

    /* insert() method for creating new record. */
    public boolean insert(Student student){
        ContentValues values = new ContentValues();
        values.put("firstname", student.getFirstname());
        values.put("email", student.getEmail());

        boolean createStatus = db.insert("students",null,values)>0;
        db.close();

        return createStatus;
    }

    public List<Student> read(){

        List<Student> mList = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id DESC";

        Cursor cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String name = cursor.getString(cursor.getColumnIndex("firstname"));
                String email = cursor.getString(cursor.getColumnIndex("email"));

                Student student = new Student();
                student.setId(id);
                student.setFirstname(name);
                student.setEmail(email);
                mList.add(student);

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return mList;
    }

    /* Method to query the total record of the student */
    public int count(){
        String sql = "SELECT * FROM students";
        int recordCOunt = db.rawQuery(sql,null).getCount();
        db.close();

        return recordCOunt;
    }
}
