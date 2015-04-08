package siolabs.badmotherfucker.Activity;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import siolabs.badmotherfucker.Adapter.AccountAdapter;
import siolabs.badmotherfucker.Entity.Account;
import siolabs.badmotherfucker.R;

public class ViewAccount extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);
        
        RecyclerView accRecyclerView = (RecyclerView)findViewById(R.id.accountList);
        accRecyclerView.setHasFixedSize(true);
        LinearLayoutManager accountLlm = new LinearLayoutManager(this);
        accountLlm.setOrientation(LinearLayoutManager.VERTICAL);
        
        accRecyclerView.setLayoutManager(accountLlm);

        AccountAdapter aa = new AccountAdapter(createAccount(4));
        
        accRecyclerView.setAdapter(aa);
    }

    private List<Account> createAccount(int i) {
        List<Account> accList = new ArrayList<Account>();
        for(int j = 0; j<i;j++){
            Account ac = new Account();
            ac.setAccountName("Account" + i + "_" + j);
            ac.setBalanceAmt(1000);
            accList.add(ac);
        }
        Log.d("DEBUG","Size of list "+ accList.size());
        return  accList;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_account, menu);
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
