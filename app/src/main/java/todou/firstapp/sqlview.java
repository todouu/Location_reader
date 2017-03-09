package todou.firstapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by EvanPeng on 2/25/17.
 */

public class sqlview extends AppCompatActivity {
    SQLiteExample mSQLiteExample;
    Button mSQLSubmitButton;
    Cursor mSQLCursor;
    SimpleCursorAdapter mSQLCursorAdapter;
    private static final String TAG = "SQLActivity";
    SQLiteDatabase mSQLDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_view_layout);

        mSQLiteExample = new SQLiteExample(this);
        mSQLDB = mSQLiteExample.getWritableDatabase();

        mSQLSubmitButton = (Button) findViewById(R.id.sql_add_row_button);
        mSQLSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSQLDB != null){
                    ContentValues vals = new ContentValues();
                    vals.put(DBContract.DemoTable.COLUMN_lat, ((EditText)findViewById(R.id.sql_input_lat)).getText().toString());
                    vals.put(DBContract.DemoTable.COLUMN_lon, ((EditText)findViewById(R.id.sql_input_lon)).getText().toString());
                    mSQLDB.insert(DBContract.DemoTable.TABLE_NAME,null,vals);
                    populateTable();
                } else {
                    Log.d(TAG, "Unable to access database for writing.");
                }
            }
        });

        populateTable();
    }
    private void populateTable(){
        if(mSQLDB != null) {
            try {
                if(mSQLCursorAdapter != null && mSQLCursorAdapter.getCursor() != null){
                    if(!mSQLCursorAdapter.getCursor().isClosed()){
                        mSQLCursorAdapter.getCursor().close();
                    }
                }
                mSQLCursor = mSQLDB.query(DBContract.DemoTable.TABLE_NAME,
                        new String[]{DBContract.DemoTable._ID, DBContract.DemoTable.COLUMN_lat,
                                DBContract.DemoTable.COLUMN_lon}, DBContract.DemoTable.COLUMN_lon + " > ?", new String[]{"100"}, null, null, null);
                ListView SQLListView = (ListView) findViewById(R.id.sql_list_view);
                mSQLCursorAdapter = new SimpleCursorAdapter(this,
                        R.layout.sql_data,
                        mSQLCursor,
                        new String[]{DBContract.DemoTable.COLUMN_lat, DBContract.DemoTable.COLUMN_lon},
                        new int[]{R.id.sql_listview_lat, R.id.sql_listview_lon},
                        0);
                SQLListView.setAdapter(mSQLCursorAdapter);
            } catch (Exception e) {
                Log.d(TAG, "Error loading data from database");
            }
        }
    }
}

class SQLiteExample extends SQLiteOpenHelper {

    public SQLiteExample(Context context) {
        super(context, DBContract.DemoTable.DB_NAME, null, DBContract.DemoTable.DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBContract.DemoTable.SQL_CREATE_DEMO_TABLE);

        ContentValues testValues = new ContentValues();
        testValues.put(DBContract.DemoTable.COLUMN_lon, 42.5);
        testValues.put(DBContract.DemoTable.COLUMN_lat, 12.5);
        db.insert(DBContract.DemoTable.TABLE_NAME,null,testValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DBContract.DemoTable.SQL_DROP_DEMO_TABLE);
        onCreate(db);
    }
}

final class DBContract {
    private DBContract() {};

    public final class DemoTable implements BaseColumns {
        public static final String DB_NAME = "location_db";
        public static final String TABLE_NAME = "location";
        public static final String COLUMN_lat = "Latitude";
        public static final String COLUMN_lon = "Longtitude";
        public static final int DB_VERSION = 2;


        public static final String SQL_CREATE_DEMO_TABLE = "CREATE TABLE " +
                DemoTable.TABLE_NAME + "(" + DemoTable._ID + " INTEGER PRIMARY KEY NOT NULL," +
                DemoTable.COLUMN_lat + " VARCHAR(255)," +
                DemoTable.COLUMN_lon + " VARCHAR(255));";

        public static final String SQL_DROP_DEMO_TABLE = "DROP TABLE IF EXISTS " + DemoTable.TABLE_NAME;
    }
}

