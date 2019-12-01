package link.radsapolaznicima.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkWStudDatabaseHelper extends SQLiteOpenHelper {

    public WorkWStudDatabaseHelper(Context context) {
        super(context, WorkWStudDatabaseContract.DATABASE_NAME, null, WorkWStudDatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WorkWStudDatabaseContract.ItemTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(WorkWStudDatabaseContract.ItemTable.SQL_DELETE_TABLE);
        onCreate(db);
    }
}
