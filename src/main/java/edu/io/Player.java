package edu.io;

import edu.io.token.*;

public class Player 
{
    public PlayerToken token;
    private double gold;
    private Token pickaxeToken = new EmptyToken();

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
        if (token instanceof GoldToken goldToken) 
        {
            double amount = (goldToken).amount();
            if (pickaxeToken instanceof PickaxeToken pickaxe) 
            {   
                pickaxe.useWith(goldToken)
                    .ifWorking(() ->
                    {
                        gainGold(amount * pickaxe.gainFactor());
                    })
                    .ifBroken(() ->
                    {
                        gainGold(amount);
                        pickaxeToken = new EmptyToken();
                    })
                    .ifIdle(() -> 
                    {
                        gainGold(amount);
                    });
            } else 
            {
                gainGold(amount);
            }

            System.out.println("Gold collected: " + this.gold());
        }

        else if (token instanceof PickaxeToken pickaxeToken)
        {
            this.pickaxeToken = pickaxeToken;
        }

        else if (token instanceof AnvilToken)
        {
            if (pickaxeToken instanceof PickaxeToken pickaxe) 
            {
                pickaxe.repair();
                System.out.println("naprawione");
            } else 
            {
                System.out.println("Brak kilofa");
            }
        }
    }
}
