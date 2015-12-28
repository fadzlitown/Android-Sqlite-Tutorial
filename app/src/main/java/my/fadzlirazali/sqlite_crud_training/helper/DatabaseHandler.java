package my.fadzlirazali.sqlite_crud_training.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by agmostudio on 12/27/15.
 *
 * - handle all the SQLite database connection
 * - sql queries are being called here
 *
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    protected static final String DATABASE_NAME= "StudentDB";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE students "+
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "firstname TEXT, "+
                "email TEXT) ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);
        onCreate(db);
    }
}
