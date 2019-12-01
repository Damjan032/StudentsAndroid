package link.radsapolaznicima;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import link.radsapolaznicima.core.WorkWStudDatabaseContract;
import link.radsapolaznicima.core.WorkWStudDatabaseHelper;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkWStudDatabaseHelper dbHelper = new WorkWStudDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
    }

    void addBt(View v){
        EditText etFirstName = (EditText) findViewById(R.id.etFirstName);
        EditText etLastName = (EditText) findViewById(R.id.etLastName);
        EditText etNum = (EditText) findViewById(R.id.etNum);
        EditText etYear = (EditText) findViewById(R.id.etYear);
        TextView tvInfo = (TextView) findViewById(R.id.tvInfo);
        String firstName, lastName, num, year;
        int numI, yearI;
        try {
            firstName = etFirstName.getText().toString().trim();
            lastName = etLastName.getText().toString().trim();
            num = etNum.getText().toString().trim();
            year = etYear.getText().toString().trim();
            numI = Integer.parseInt(num);
            yearI = Integer.parseInt(year);

            ContentValues values = new ContentValues();

            if(!firstName.trim().equals("") && !lastName.trim().equals("") && numI>=0 && numI<=100 && yearI>=0 && yearI<=5) {
                values.put(WorkWStudDatabaseContract.ItemTable.COLUMN_NAME_FNAME, firstName);
                values.put(WorkWStudDatabaseContract.ItemTable.COLUMN_NAME_LNAME, lastName);
                values.put(WorkWStudDatabaseContract.ItemTable.COLUMN_NAME_POINTS, numI);
                values.put(WorkWStudDatabaseContract.ItemTable.COLUMN_NAME_YEAR, yearI);
                long newRowId = db.insert(WorkWStudDatabaseContract.ItemTable.TABLE_NAME, null, values);
                etFirstName.setText("");
                etLastName.setText("");
                etNum.setText("");
                etYear.setText("");

                tvInfo.setText("  Last added: " + newRowId + " " + firstName + " " + lastName + " " + num + " " + year);
            }
            else{
                tvInfo.setText("   Bad arguments. Try again. Name can not be empty. \n   " +
                        "Points must be in the range 0 to 100. \n   Year must be in the range 1 to 5.");
            }
        }
        catch (Exception e){
            tvInfo.setText("   Bad arguments. Try again. Name can not be empty. \n   " +
                    "Points must be in the range 0 to 100. \n   Year must be in the range 1 to 5.");
        }

    }

    void deleteBt(View v) {
        db.delete(WorkWStudDatabaseContract.ItemTable.TABLE_NAME, null, null);
    }

    void displayBt(View v){
        Cursor cursor =  db.rawQuery("SELECT * FROM " + WorkWStudDatabaseContract.ItemTable.TABLE_NAME +
                " WHERE(" + WorkWStudDatabaseContract.ItemTable.COLUMN_NAME_POINTS + ">80) " +
                "ORDER BY "+ WorkWStudDatabaseContract.ItemTable.COLUMN_NAME_POINTS+" DESC ",null);
        int num = 1;
        StringBuilder sb = new StringBuilder("Top students (over 80 points): \n");
        while (cursor.moveToNext() && num <=5){
            String firstName = cursor.getString(1);
            String lastName = cursor.getString(2);
            int points = cursor.getInt(3);
            int year = cursor.getInt(4);
            String stud = "  "+num + ". Name: " + firstName + " " + lastName + ", year: " + year + ", points: " + points;
            sb.append(stud+"\n");
            Log.i("IME: ", firstName);
            num++;
        }
        if(num == 1){
            sb.delete(0,sb.length()-1);
            sb.append("We don't have student with more then 80 points.");
        }
        TextView tvDisplay = (TextView) findViewById(R.id.tvDisplay);
        tvDisplay.setText(sb.toString());
    }
}
