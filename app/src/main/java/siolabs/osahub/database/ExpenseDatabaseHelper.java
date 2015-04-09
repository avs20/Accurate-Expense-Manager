package siolabs.osahub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import siolabs.osahub.Entity.Account;

/**
 * Created by PIR on 4/9/2015.
 */
public class ExpenseDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="expense.sqlite";
    private static final int VERSION = 1;
    public static final String COLUMN_ACCOUNT_ID ="_id" ;

    private final String TABLE_ACCOUNT = "account";
    public static final String COLUMN_ACCOUNT_NAME = "name";
    public static final String COLUMN_ACCOUNT_BALANCE = "balance";

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

    public List<Account> getAccount(String id){

        List<Account> list = new ArrayList<Account>();
        Cursor cursor = getReadableDatabase().query(TABLE_ACCOUNT,null,null,null,null,null,null);

        if (cursor != null) {
            cursor.moveToFirst();
            Account account = new Account();
            account.setAccountName(cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ACCOUNT_NAME)));
            account.setBalanceAmt(cursor.getFloat(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ACCOUNT_BALANCE)));
            account.setId(cursor.getLong(cursor.getInt(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ACCOUNT_ID))));
            list.add(account);


            while (!cursor.isLast()) {
                cursor.moveToNext();
                account = new Account();
                account.setAccountName(cursor.getString(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ACCOUNT_NAME)));
                account.setBalanceAmt(cursor.getFloat(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ACCOUNT_BALANCE)));
                account.setId(cursor.getLong(cursor.getColumnIndexOrThrow(ExpenseDatabaseHelper.COLUMN_ACCOUNT_ID)));
                list.add(account);
            }

        }
        cursor.close();
        return list;

    }
}
