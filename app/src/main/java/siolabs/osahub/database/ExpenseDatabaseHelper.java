package siolabs.osahub.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

import siolabs.osahub.Entity.Account;
import siolabs.osahub.Entity.Category;
import siolabs.osahub.Entity.Transaction;
import siolabs.osahub.R;

/**
 * Created by PIR on 4/9/2015.
 */
public class ExpenseDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="expense.sqlite";
    private static final int VERSION = 1;
    public static final String COLUMN_ACCOUNT_ID ="_id" ;

    //AMOUNT TABLE
    private final String TABLE_ACCOUNT = "account";
    public static final String COLUMN_ACCOUNT_NAME = "name";
    public static final String COLUMN_ACCOUNT_BALANCE = "balance";
    
    private  final String TABLE_TRANSACTION = "transactions";
    public  static final String COLUMN_TRANSACTION_ACCOUNT = "acc_name";
    public  static final String COLUMN_TRANSACTION_CATEGORY = "cat_name";
    public  static final String COLUMN_TRANSACTION_AMOUNT = "amount";
    public  static final String COLUMN_TRANSACTION_ISEXPENSE ="is_expense";
    public  static final String COLUMN_TRANSACTION_DATE = "trans_date";

    private final String TABLE_CATEGORY = "category";
    public static final String COLUMN_CATEGORY_NAME = "name";
    public static final String COLUMN_CATEGORY_AMT = "spent_amt";
    private Context ctx;



    public ExpenseDatabaseHelper(Context context){
        super(context, DB_NAME, null, VERSION); this.ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the account table
        db.execSQL(" CREATE TABLE account (_id integer primary key autoincrement," +
                                            "name varchar(50)," +
                                            "balance real)");

        //create the categories table
        db.execSQL(" CREATE TABLE category (_id integer primary key autoincrement," +
                                               "name text," +
                                                "spent_amt real)");


        
        //create the transaction table
        String transTable = " CREATE TABLE transactions (_id integer primary key autoincrement, " +
                " acc_name text," +
                " cat_name text," +
                " amount real," +
                " is_expense varchar(10)," +
                "trans_date integer)";
        db.execSQL(transTable);
        
        insertCategoryValues(db);

    }

    private void insertCategoryValues(SQLiteDatabase db) {



        String[] categories = ctx.getResources().getStringArray(R.array.categoryItems);
        String sql = "INSERT INTO " +TABLE_CATEGORY + "(name,spent_amt) VALUES(?,?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        db.beginTransaction();
        for(String s : categories){
            stmt.clearBindings();
            stmt.bindString(1, s);
            stmt.bindDouble(2,0.0);
            stmt.execute();
        }
        db.setTransactionSuccessful();
        db.endTransaction();

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
    
    public long insertTransaction(Transaction t){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TRANSACTION_AMOUNT, t.getAmount());
        cv.put(COLUMN_TRANSACTION_CATEGORY, t.getCatName());
        cv.put(COLUMN_TRANSACTION_ACCOUNT, t.getAccName());
        cv.put(COLUMN_TRANSACTION_DATE, t.getDateStr());
        return getWritableDatabase().insert(TABLE_TRANSACTION, null,cv);
    }

    public List<Category> getAllCategories(String category){
        List<Category> catList =  new ArrayList<Category>();

        if(category.length()<1){
            Cursor cursor = getReadableDatabase().query(TABLE_CATEGORY,null,null,null,null,null,null);

            if(cursor.moveToFirst() && cursor.getCount()!=0){
                //cursor is not empty
                Category cat = new Category();
                cat.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME)));
                cat.setSpentAmt(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_AMT)));
                catList.add(cat);

                while(!cursor.isLast()){
                    cursor.moveToNext();
                    cat = new Category();
                    cat.setCategoryName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_NAME)));
                    cat.setSpentAmt(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY_AMT)));
                    catList.add(cat);

                }

            }
            cursor.close();



        }

        return catList;
    }

    //getTransaction by Category
    public List<Transaction> getTransactionByCategory(String cat)
    {
        List<Transaction> list = new ArrayList<Transaction>();
        Cursor cursor = null;
        //if account is blank then get all transaction ordered by date
        if(cat.length() < 1) {
             cursor = getReadableDatabase().query(TABLE_TRANSACTION, null, null, null, null, null, null);
        }else {
            cursor = getReadableDatabase().query(TABLE_TRANSACTION, null,"name=", new String[]{cat},null,null,null );
        }

            if ((cursor.moveToFirst()) && cursor.getCount() !=0){
                Transaction t = new Transaction();
                t.setAmount(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_AMOUNT)));
                t.setCatName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_CATEGORY)));
                t.setAccName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_ACCOUNT)));
                t.setDateStr(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_DATE)));

                list.add(t);


                while(!cursor.isLast()){
                    cursor.moveToNext();
                    t = new Transaction();
                    t.setAmount(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_AMOUNT)));
                    t.setCatName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_CATEGORY)));
                    t.setAccName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_ACCOUNT)));
                    t.setDateStr(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_DATE)));

                    list.add(t);

                }
            }
            cursor.close();



        return list;
    }


    public List<Transaction> getTransaction(String account)
    {
        List<Transaction> list = new ArrayList<Transaction>();
        //if account is blank then get all transaction ordered by date
        if(account.length() < 1){
            Cursor cursor = getReadableDatabase().query(TABLE_TRANSACTION,null,null,null,null,null,null );
            if ((cursor.moveToFirst()) && cursor.getCount() !=0){
                Transaction t = new Transaction();
                t.setAmount(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_AMOUNT)));
                t.setCatName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_CATEGORY)));
                t.setAccName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_ACCOUNT)));
                t.setDateStr(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_DATE)));
               
                list.add(t);
                

                while(!cursor.isLast()){
                    cursor.moveToNext();
                    t = new Transaction();
                    t.setAmount(cursor.getFloat(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_AMOUNT)));
                    t.setCatName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_CATEGORY)));
                    t.setAccName(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_ACCOUNT)));
                    t.setDateStr(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TRANSACTION_DATE)));

                    list.add(t);

                }
            }
            cursor.close();

        }
        
       return list;
    }

    public List<Account> getAccount(String id){

        List<Account> list = new ArrayList<Account>();
        Cursor cursor = getReadableDatabase().query(TABLE_ACCOUNT,null,null,null,null,null,null);

        if ((cursor.moveToFirst()) && cursor.getCount() !=0){



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
