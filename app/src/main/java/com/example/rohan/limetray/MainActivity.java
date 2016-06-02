package com.example.rohan.limetray;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import retrofit2.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    RESTAdapter restAdapter;
    List<Expense> expenses;
    TransactionsAdapter adapter;
    ListView transactionList;

    Call<Response> request;

    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        transactionList = (ListView) findViewById(R.id.transactionListView);
        transactionList.setOnItemClickListener(this);
        showDialog();
        getExpensesFromAPI();
        hideDialog();
    }

    public void showDialog() {
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Fetching expenses...");
        mDialog.show();
    }

    public void hideDialog() {
        mDialog.dismiss();
    }

    public void getExpensesFromAPI() {

        restAdapter = new RESTAdapter(Constants.BASE_URL);

        request = restAdapter.getApi().getExpenses(Constants.BLOB_ID);

        //getTransactionsList
        request.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                setData(response);
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failed to get API data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setData(retrofit2.Response<Response> response) {
        expenses = response.body().getExpenseList();
        adapter = new TransactionsAdapter(getApplicationContext(), expenses);
        transactionList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {

        final PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        popupMenu.getMenuInflater().inflate(R.menu.transaction_state_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                //updateExpenseState
                Expense expense = (Expense) parent.getAdapter().getItem(position);
                expense.setState(item.getTitle().toString());

                Response response = new Response();
                response.setExpenseList(expenses);

                //Send back response
                Call<Response> call = restAdapter.getApi().updateExpenseState(response);

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    }
                });

                adapter.notifyDataSetChanged();
                return true;
            }
        });

        popupMenu.show();
    }

}
