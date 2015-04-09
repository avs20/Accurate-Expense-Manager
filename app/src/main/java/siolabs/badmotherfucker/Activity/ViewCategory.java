package siolabs.badmotherfucker.Activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import siolabs.badmotherfucker.Adapter.AccountAdapter;
import siolabs.badmotherfucker.Adapter.CategoryAdapter;
import siolabs.badmotherfucker.Entity.Account;
import siolabs.badmotherfucker.Entity.Category;
import siolabs.badmotherfucker.R;

public class ViewCategory extends ActionBarActivity {

    
    CategoryAdapter ca;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);
        
        //get all the existing accounts
        RecyclerView accRecyclerView = (RecyclerView)findViewById(R.id.catList);
        accRecyclerView.setHasFixedSize(true);
        LinearLayoutManager accountLlm = new LinearLayoutManager(this);
        accountLlm.setOrientation(LinearLayoutManager.VERTICAL);
        accRecyclerView.setLayoutManager(accountLlm);

        //category  testin
        ca = new CategoryAdapter(createCategory(5));
        accRecyclerView.setAdapter(ca);
        
        //show the button 
//        Button addNewAcc = (Button) findViewById(R.id.addNewCatBtn);
//        addNewAcc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showAddNewCategoryDialog();
//            }
//        });
//
        
        
         

        
        
    }


    
    private List<Category> createCategory(int i ){
        List<Category> catList = new ArrayList<Category>();
        for(int j = 0 ;j < i ;j ++){
            Category cat = new Category();
            cat.setCategoryName("Category__" + i + "  " + j);
            cat.setSpentAmt(1000);
            catList.add(cat);
        }
        return  catList;
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
                
                ca.notifyItemChanged(ca.getItemCount());
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
