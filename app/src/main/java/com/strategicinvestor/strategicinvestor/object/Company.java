package com.strategicinvestor.strategicinvestor.object;

import android.content.ComponentName;

public class Company {

    private String name;
    private String ticker;
    private double price;

    public Company(String name, String ticker, double price)
    {
        this.name = name; this.ticker = ticker; this.price = price;
    }

    public Company(){
        this("", "", 0);
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getTicker() {
        return ticker;
    }
}
