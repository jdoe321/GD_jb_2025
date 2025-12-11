package edu.io.player;

import java.util.Objects;

public class Vitals 
{
    private int hydration;
    private Runnable onDeathCallback;

    public Vitals(int hydration) 
    {
        if ((hydration < 0) || (hydration > 100)) 
            throw new IllegalArgumentException("hydration musi byc w zakresie [0;100]");
        
        this.hydration = hydration;
        onDeathCallback = () -> {};
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
        if (hydration > 100) 
        {
            hydration = 100;
        }
    }

    public void dehydrate(int amount) 
    {
        if (amount < 0) 
            throw new IllegalArgumentException("amount nie moze byc ujemny");
        
        hydration -= amount;
        if (hydration <= 0) 
        {
            hydration = 0;
            onDeathCallback.run();
        }
    }

    public boolean isAlive()
    {
        return hydration > 0;
    }

    public void setOnDeathHandler(Runnable callback) 
    {
        this.onDeathCallback = Objects.requireNonNull(callback, "callback cannot be null");
    }
}
