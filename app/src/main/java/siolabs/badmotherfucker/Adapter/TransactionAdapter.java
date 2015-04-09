package siolabs.badmotherfucker.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import siolabs.badmotherfucker.R;

/**
 * Created by ashutoshsingh on 09-04-2015.
 */
public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>{


    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
