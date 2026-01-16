package edu.io.token;

public class PickaxeToken extends Token implements Tool, Repairable
{
    private double gainFactor;
    private int durability;
    private int startingDurability;
    private Token withToken;

    public PickaxeToken(double gainFactor, int durability)
    {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0.0) 
            throw new IllegalArgumentException("gainFactor nie moze byc ujemny");
        if (durability <= 0) 
            throw new IllegalArgumentException("durability musi byc dodatnie");
        
        this.gainFactor = gainFactor;
        this.durability = durability;
        this.startingDurability = durability;
    }

    public PickaxeToken(double gainFactor)
    {
        this(gainFactor, 3);
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

    public PickaxeToken useWith(Token withToken) 
    {
        this.withToken = withToken;

        return this;
    }

    public PickaxeToken ifWorking(Runnable action) 
    {
        if (!isBroken() && withToken instanceof GoldToken) 
        {
            this.use();
            this.withToken = new EmptyToken(); //hotfix
            action.run();
        }     
        
        return this;
    }

    public PickaxeToken ifBroken(Runnable action) 
    {
        if (isBroken() && withToken instanceof GoldToken) 
        {
            action.run();
        }
        return this;
    }

    public PickaxeToken ifIdle(Runnable action) 
    {

        return this;
    }

    public void repair()
    {
        this.durability = this.startingDurability;
    }

}
