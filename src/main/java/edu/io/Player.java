package edu.io;

import edu.io.token.PlayerToken;

public class Player 
{
    public PlayerToken token;
    private double gold;

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
        gold += amount;
    }

    public void loseGold(double amount) 
    {
        gold -= amount;
        if (gold < 0) 
        {
            gold = 0;
            throw new IllegalArgumentException("zabraklo zlota");
        }
    }
    
}
