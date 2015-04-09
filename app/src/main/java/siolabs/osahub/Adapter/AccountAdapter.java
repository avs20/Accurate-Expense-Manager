package siolabs.osahub.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import siolabs.osahub.Entity.Account;
import siolabs.osahub.R;
import siolabs.osahub.database.ExpenseDatabaseHelper;

/**
 * Created by ashutoshsingh on 08-04-2015.
 * The adapter class for the recycler view*
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> accList;
    private ExpenseDatabaseHelper dbHelper;
    private Cursor cursor;
    private Context context;
    private String id;
    
    public AccountAdapter(Context context, ExpenseDatabaseHelper dbHelper , String id){

        this.dbHelper = dbHelper;
        this.context  = context;
        this.id = id;
        this.accList = dbHelper.getAccount(id);
        
    }

    public void updateList(){
        this.accList = dbHelper.getAccount(id);
        notifyDataSetChanged();
    }



    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.account_card_layout, viewGroup, false);

        return new AccountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AccountViewHolder amountViewHolder, int i) {
        
        Account acc = accList.get(i);
        amountViewHolder.balText.setText("Balance");
        amountViewHolder.accountName.setText(acc.getAccountName());
        amountViewHolder.balance.setText(acc.getBalanceAmt()+"");

    }

    @Override
    public int getItemCount() {
        return accList.size();
    }

    //Class for the view holder pattern
    public static class AccountViewHolder extends RecyclerView.ViewHolder {

        protected TextView accountName;
        protected TextView balance;
        protected TextView balText;
        protected View divider;


        public AccountViewHolder(View itemView) {
            super(itemView);
            accountName = (TextView) itemView.findViewById(R.id.accNameTextView);
            balance = (TextView) itemView.findViewById(R.id.accAmountTextView);
            balText = (TextView) itemView.findViewById(R.id.accBalTextView);

        }
    }
}
