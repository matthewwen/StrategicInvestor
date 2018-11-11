package com.strategicinvestor.strategicinvestor.object;


public class Company {

    private String name;
    private String ticker;
    private double price;
    private boolean waitlist;
    private int color;

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

    public int getColor() {
        return color;
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

    public void setColor(int color)
    {
        this.color = color;
    }

}
