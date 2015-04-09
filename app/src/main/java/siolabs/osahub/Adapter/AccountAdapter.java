package siolabs.osahub.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import siolabs.osahub.Entity.Account;
import siolabs.osahub.R;

/**
 * Created by ashutoshsingh on 08-04-2015.
 * The adapter class for the recycler view*
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private List<Account> accList;
    
    public AccountAdapter(List<Account> accountList){
        this.accList = accountList;
        
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
