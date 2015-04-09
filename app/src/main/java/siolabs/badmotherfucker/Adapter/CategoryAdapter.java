package siolabs.badmotherfucker.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import siolabs.badmotherfucker.Entity.Account;
import siolabs.badmotherfucker.Entity.Category;
import siolabs.badmotherfucker.R;

/**
 * Created by ashutoshsingh on 08-04-2015.
 * The adapter class for the recycler view*
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categoryList;

    public CategoryAdapter(List<Category> catList){
        this.categoryList = catList;
        
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.account_category_layout, viewGroup, false);

        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder catViewHolder, int i) {
        
        Category cat = categoryList.get(i);
        catViewHolder.catName.setText(cat.getCategoryName());
        catViewHolder.expense.setText(cat.getSpentAmt() + "");
        catViewHolder.expenseText.setText("Amount Spent : ");

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    //Class for the view holder pattern
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        protected TextView catName;
        protected TextView expense;
        protected TextView expenseText;
        protected View divider;


        public CategoryViewHolder(View itemView) {
            super(itemView);
            catName = (TextView) itemView.findViewById(R.id.catNameTextView);
            expense = (TextView) itemView.findViewById(R.id.catAmountTextView);
            expenseText = (TextView) itemView.findViewById(R.id.catExpenseTextView);

        }
    }
}
