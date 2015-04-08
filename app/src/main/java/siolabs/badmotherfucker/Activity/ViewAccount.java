package siolabs.badmotherfucker.Activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        
        //get all the existing accounts
        RecyclerView accRecyclerView = (RecyclerView)findViewById(R.id.accountList);
        accRecyclerView.setHasFixedSize(true);
        LinearLayoutManager accountLlm = new LinearLayoutManager(this);
        accountLlm.setOrientation(LinearLayoutManager.VERTICAL);
        accRecyclerView.setLayoutManager(accountLlm);
        AccountAdapter aa = new AccountAdapter(createAccount(4));
        accRecyclerView.setAdapter(aa);
        
        //show the button 
        Button addNewAcc = (Button) findViewById(R.id.addNewAccBtn);
        addNewAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddNewAccountDialog();
            }
        });
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
    
    
    /* function to show the add new Accounr Dialog box*/
    private void showAddNewAccountDialog(){
        // Create custom dialog object
        final Dialog dialog = new Dialog(this);
        // hide to default title for Dialog
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setTitle("Add New Account");

        // inflate the layout dialog_layout.xml and set it as contentView
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_add_account,  null, false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(view);
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));

        EditText accNameEditText = (EditText) dialog.findViewById(R.id.addAccNameEditText);
        EditText accBalEditText = (EditText) dialog.findViewById(R.id.addAccBalEditText);
        Button doneBtn = (Button) dialog.findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO save the account
                Toast.makeText(getApplicationContext(),"DOne Clicked",Toast.LENGTH_LONG).show();
                //TODO close the dialog and return to View Account Activity
                dialog.dismiss();
            }
        });
        
        Button cancelBtn = (Button) dialog.findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO close the dialog and return to View Account Activity
                Toast.makeText(getApplicationContext(),"Cancel  Clicked",Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });
        
        //display dialog
        dialog.show();
    }
}
