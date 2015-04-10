package siolabs.osahub.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import siolabs.osahub.Entity.Transaction;
import siolabs.osahub.R;
import siolabs.osahub.database.ExpenseDatabaseHelper;

/**
 * Created by ashutoshsingh on 09-04-2015.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{
    
    private List<Transaction> transactionList;
    Context ctx ;
    ExpenseDatabaseHelper dbHelper;
    String id;
    
    public TransactionAdapter(Context context,  ExpenseDatabaseHelper dbHelper, String id){
        this.ctx  = context;
        this.dbHelper = dbHelper;
        this.id = id;
        this.transactionList = dbHelper.getTransaction(id);
    }
    
    public void updateList(List<Transaction> data){
        
        transactionList = dbHelper.getTransaction(id);
        notifyDataSetChanged();
        
    }
    


    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.transaction_list_item_layout,parent,false);

        return new TransactionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        Transaction t = transactionList.get(position);
        holder.catName.setText(t.getCatName());
        holder.amtSpent.setText("Rs "+ t.getAmount());
        holder.date.setText(t.getDateStr());
        holder.memo.setText(t.getMemo());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder{
        public TextView catName;
        public TextView amtSpent;
        public TextView date;
        public TextView memo;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            catName = (TextView) itemView.findViewById(R.id.catTransName);
            amtSpent = (TextView) itemView.findViewById(R.id.transAmountSpent);
            date  = (TextView) itemView.findViewById(R.id.catTransDate);
            memo = (TextView) itemView.findViewById(R.id.catTransMemo);
            
        }
    }
    
}
