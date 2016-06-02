package com.example.rohan.limetray;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rohan on 02-Jun-16.
 */
public class Response {

    @SerializedName("expenses")
    private List<Expense> expenseList;

    public void setExpenseList(List<Expense> allExpenses) {
        this.expenseList = allExpenses;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

}
