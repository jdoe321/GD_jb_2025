package edu.io.token;

public class PickaxeToken extends Token
{
    private double gainFactor;
    private int durability;

    public PickaxeToken(double gainFactor, int durability)
    {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor < 0.0) 
            throw new IllegalArgumentException("gainFactor nie moze byc ujemny");
        if (durability < 0) 
            throw new IllegalArgumentException("durability musi byc dodatnie");
        
        this.gainFactor = gainFactor;
        this.durability = durability;
    }

    public PickaxeToken(double gainFactor)
    {
        this(gainFactor, 10);
    }
    
    public PickaxeToken()
    {
        this(1.5);
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
            throw new IllegalStateException("Kilof jest juz zniszczony");
        
        durability--;
    }

    public boolean isBroken() 
    {
        return durability <= 0;
    }
}
