package link.radsapolaznicima.core;

import android.provider.BaseColumns;

public class WorkWStudDatabaseContract {

    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "WorkWithStudentsDatabase";

    private WorkWStudDatabaseContract(){}


    public static class ItemTable implements BaseColumns {
        public static final String TABLE_NAME="students";

        public static final String COLUMN_NAME_FNAME="firstName";
        public static final String COLUMN_NAME_LNAME="lastName";
        public static final String COLUMN_NAME_POINTS="numOfPoints";
        public static final String COLUMN_NAME_YEAR="year";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + "(" +
                _ID + " INTEGER PRIMARY KEY,"+
                COLUMN_NAME_FNAME + " TEXT," +
                COLUMN_NAME_LNAME + " TEXT," +
                COLUMN_NAME_POINTS + " INTEGER," +
                COLUMN_NAME_YEAR + " INTEGER)";


        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }
}
