package com.strategicinvestor.strategicinvestor.object;

import android.content.ComponentName;

public class Company {

    private String name;
    private String ticker;
    private double price;
    private boolean waitlist;

    public Company(String name, String ticker, double price, boolean waitlist)
    {
        this.name = name; this.ticker = ticker; this.price = price; this.waitlist = waitlist;
    }

    public Company(){
        this("", "", 0, false);
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

    public boolean getWaitlist() {
        return waitlist;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setWaitList(boolean b)
    {
        this.waitlist = b;
    }
}
