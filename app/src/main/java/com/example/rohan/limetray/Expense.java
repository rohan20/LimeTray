package com.example.rohan.limetray;

/**
 * Created by Rohan on 02-Jun-16.
 */
public class Expense {

    private long amount;
    private String category;
    private String description;
    private long id;
    private String state;
    private String time;

    //getters
    public long getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getTime() {
        return time;
    }

    //setters

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
//
//        "amount": 240,
//        "category": "Recharge",
//        "description": "Recharge of Tata Sky DTH 1042921111",
//        "id": "1199965180",
//        "state": "unverified",
//        "time": "2015-09-06T00:46:17.000Z"
