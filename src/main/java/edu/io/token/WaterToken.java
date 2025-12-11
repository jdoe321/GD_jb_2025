package edu.io.token;

public class WaterToken extends Token
{
    private int amount;

    public WaterToken() 
    {
        this(20);
    }

    public WaterToken(int initialAmount) 
    {
        super(Label.WATER_TOKEN_LABEL);
        this.amount = initialAmount;
    }

    public int amount() 
    {
        return amount;
    }


}
