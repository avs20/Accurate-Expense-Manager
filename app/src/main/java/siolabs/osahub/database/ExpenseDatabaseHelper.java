package siolabs.osahub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import siolabs.osahub.Entity.Account;

/**
 * Created by PIR on 4/9/2015.
 */
public class ExpenseDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="expense.sqlite";
    private static final int VERSION = 1;

    private final String TABLE_ACCOUNT = "account";
    private final String COLUMN_ACCOUNT_NAME = "name";
    private final String COLUMN_ACCOUNT_BALANCE = "balance";

    public ExpenseDatabaseHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the account table
        db.execSQL(" CREATE TABLE account (_id integer primary key autoincrement," +
                                            "name varchar(50)," +
                                             "balance real)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO when the code is updated for the datbase schema
    }

    public long insertAccount( Account account){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ACCOUNT_NAME, account.getAccountName());
        cv.put(COLUMN_ACCOUNT_BALANCE, account.getBalanceAmt());
        return getWritableDatabase().insert(TABLE_ACCOUNT,null, cv);
    }
}
