package edu.io.player;

public class Vitals 
{
    private int hydration;

    public Vitals(int hydration) 
    {
        if (hydration < 0) 
            throw new IllegalArgumentException("hydration nie moze byc ujemny");
        
        this.hydration = hydration;
    }

    public Vitals() 
    {
        this(100);
    }

    public int hydration() 
    {
        return hydration;
    }

    public void hydrate(int amount) 
    {
        if (amount < 0) 
            throw new IllegalArgumentException("amount nie moze byc ujemny");
        
        hydration += amount;
    }

    public void dehydrate(int amount) 
    {
        if (amount < 0) 
            throw new IllegalArgumentException("amount nie moze byc ujemny");
        
        hydration -= amount;
        if (hydration < 0) 
            hydration = 0;
    }

}
