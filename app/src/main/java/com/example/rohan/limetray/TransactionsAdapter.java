package com.example.rohan.limetray;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rohan on 02-Jun-16.
 */
public class TransactionsAdapter extends BaseAdapter {
    Context mContext;
    List<Expense> mExpenses;

    public TransactionsAdapter(Context mContext, List<Expense> mExpenses) {
        this.mContext = mContext;
        this.mExpenses = mExpenses;
    }

    @Override
    public int getCount() {
        return mExpenses.size();
    }

    @Override
    public Object getItem(int position) {
        return mExpenses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mExpenses.get(position).getId();
    }

    private class TransactionViewHolder {
        TextView transactionID;
        TextView transactionState;
        TextView transactionDescription;
        TextView transactionAmount;
        TextView transactionCategory;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.transaction_item, null);

            TransactionViewHolder transactionViewHolder = new TransactionViewHolder();
            transactionViewHolder.transactionID = (TextView) convertView.findViewById(R.id.transactionID);
            transactionViewHolder.transactionState = (TextView) convertView.findViewById(R.id.transactionState);
            transactionViewHolder.transactionCategory = (TextView) convertView.findViewById(R.id.transactionCategory);
            transactionViewHolder.transactionAmount = (TextView) convertView.findViewById(R.id.transactionAmount);
            transactionViewHolder.transactionDescription = (TextView) convertView.findViewById(R.id.transactionDescription);
            convertView.setTag(transactionViewHolder);

        }

        TransactionViewHolder transactionViewHolder = (TransactionViewHolder) convertView.getTag();
        Expense expense = (Expense) getItem(position);

        //set ID
        transactionViewHolder.transactionID.setText("ID: ");
        transactionViewHolder.transactionID.append(String.valueOf(expense.getId()));

        //set State
        if(expense.getState().equals("Verified")){
            transactionViewHolder.transactionState.setTextColor(Color.parseColor("#00b159"));
        }
        else if(expense.getState().equals("Fraud")) {
            transactionViewHolder.transactionState.setTextColor(Color.parseColor("#d02525"));
        }
        else{
            transactionViewHolder.transactionState.setTextColor(Color.parseColor("#000000"));
        }


        transactionViewHolder.transactionState.setText(expense.getState());

        //set Category
        transactionViewHolder.transactionCategory.setText(expense.getCategory());

        //set Amount
        transactionViewHolder.transactionAmount.setText("Rs. ");
        transactionViewHolder.transactionAmount.append(String.valueOf(expense.getAmount()));

        //set Description
        transactionViewHolder.transactionDescription.setText(expense.getDescription());


        return convertView;
    }
}
