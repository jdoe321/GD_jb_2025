package edu.io;

public class Gold
{
    private double amount;
    
    public Gold() 
    {
        this.amount = 1.0;
    }

    public Gold(double amount) 
    {
        if (amount < 0.0) 
            throw new IllegalArgumentException("Amount nie moze byc ujemne");

        this.amount = amount;
    }

    public double amount() 
    {
        return amount;
    }

    public void gain(double amount) 
    {
        if (amount < 0.0) 
            throw new IllegalArgumentException("Amount nie moze byc ujemne");
        
        this.amount += amount;
    }

    public void lose(double amount) 
    {
        if (amount < 0.0) 
            throw new IllegalArgumentException("Amount nie moze byc ujemne");
        if (amount > this.amount) 
            throw new IllegalArgumentException("Nie mozna stracic wiecej zlota niz sie posiada");
        
        this.amount -= amount;
    }

    
}
