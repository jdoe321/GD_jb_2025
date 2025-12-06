package edu.io.token;

public class PickaxeToken extends Token
{
    private double gainFactor;

    public PickaxeToken(double gainFactor)
    {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor < 0.0) 
            throw new IllegalArgumentException("gainFactor nie moze byc ujemny");
        
        this.gainFactor = gainFactor;
    }
    
    public PickaxeToken()
    {
        this(1.5);
    }

    public double gainFactor() 
    {
        return gainFactor;
    }
    
}
