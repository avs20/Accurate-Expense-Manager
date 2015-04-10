package siolabs.osahub.Activity;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import siolabs.osahub.Adapter.TransactionAdapter;
import siolabs.osahub.Entity.Transaction;
import siolabs.osahub.R;
import siolabs.osahub.database.ExpenseDatabaseHelper;

public class ViewAllTransaction extends ActionBarActivity {

    
    TransactionAdapter ta;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ExpenseDatabaseHelper dbHelper;
    
    //the buttons for views
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_transaction);
        
        //get the recycler view
        recyclerView = (RecyclerView) findViewById(R.id.transListRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        dbHelper = new ExpenseDatabaseHelper(this);
        
        //setting the adapter
        ta = new TransactionAdapter(this,dbHelper,"");
        recyclerView.setAdapter(ta);
        
        
        Button newTransBtn = (Button) findViewById(R.id.addNewTransactionBtn);
        newTransBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO code here to add a new transaction
                startNewTransaction();
                
            }
        });
        
    }

    private void startNewTransaction() {
        
        Intent i = new Intent(this, AddNewTransactionActivity.class);
        startActivityForResult(i,0);
    }

    private List<Transaction> createTransaction(int i) {
        
        List<Transaction> tList = new ArrayList<Transaction>();
        for(int j = 0; j < i ; j++){
            Transaction t = new Transaction();
            t.setCatName("Cat " + j);
            t.setAmount(i+j*20);
            t.setMemo("Memo " + j);
            t.setDateStr("22/12/2015");
            tList.add(t);
        }
        
        return tList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_all_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
