package edu.io.token;

public class SluiceboxToken extends Token implements Tool
{
    private double gainFactor;
    private int durability;
    private int startingDurability;
    private Token withToken;

    public SluiceboxToken() 
    {
        super(Label.SLUICEBOX_TOKEN_LABEL);

        this.gainFactor = 1.2;
        this.durability = 5;
    }

    public double gainFactor() 
    {
        return gainFactor;
    }
    public int durability() 
    {
        return durability;
    }

    public void use() 
    {
        if (durability <= 0) 
            throw new IllegalStateException("Sluicebox jest juz zniszczony");
        
        durability--;
        gainFactor -= 0.04;
    }

    public boolean isBroken() 
    {
        return durability <= 0;
    }

    public SluiceboxToken useWith(Token withToken) 
    {
        this.withToken = withToken;

        return this;
    }

    public SluiceboxToken ifWorking(Runnable action) 
    {
        if (!isBroken()) 
        {
            this.use();
            action.run();
        }
        return this;
    }

    public SluiceboxToken ifBroken(Runnable action) 
    {
        if (isBroken()) 
            action.run();
        
        return this;
    }

    public SluiceboxToken ifIdle(Runnable action) 
    {
        return this;
    }



    
}
