package edu.io;

import edu.io.token.*;

public class Player 
{
    public PlayerToken token;
    private double gold;
    private boolean hasPickaxe = false;

    public Player()
    {
        this.token = null;
        gold = 0.0;
    }

    public Player(PlayerToken token) 
    {
        this.assignToken(token);
        gold = 0.0;
    }

    public void assignToken(PlayerToken token) 
    {
        this.token = token;
        token.assignToPlayer(this);
    }

    public PlayerToken token() 
    {
        return token;
    }

    public double gold() 
    {
        return gold;
    }

    public void gainGold(double amount) 
    {
        if(amount < 0) 
            throw new IllegalArgumentException("amount nie moze byc ujemne");

        gold += amount;
    }

    public void loseGold(double amount) 
    {
        if(amount < 0) 
            throw new IllegalArgumentException("amount nie moze byc ujemne");
        
        gold -= amount;
        if (gold < 0) 
        {
            gold = 0;
            throw new IllegalArgumentException("zabraklo zlota");
        }
    }
    
    public void interactWithToken(Token token)
    {
        if (token instanceof GoldToken) 
        {
            double amount = ((GoldToken)token).amount();
            if (hasPickaxe) 
            {
                amount *= 1.5;
            }
            this.gainGold(amount);
            System.out.println("Gold collected: " + this.gold());
        }

        else if (token instanceof PickaxeToken)
        {
            hasPickaxe = true;
        }
    }
}
