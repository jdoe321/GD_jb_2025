package edu.io.token;

public class WaterToken extends Token
{
    private int amount;

    public WaterToken() 
    {
        this(10);
    }

    public WaterToken(int initialAmount) 
    {
        super(Label.WATER_TOKEN_LABEL);

        if ((initialAmount < 0) || (initialAmount > 100)) 
        {
            throw new IllegalArgumentException("Token wody musi byc w zakresie [0;100]");
        }
        this.amount = initialAmount;
    }

    public int amount() 
    {
        return amount;
    }


}
