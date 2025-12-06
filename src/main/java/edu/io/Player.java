package edu.io;

import edu.io.token.*;

public class Player 
{
    public PlayerToken token;
    private final Gold gold = new Gold(0.0);
    private Token pickaxeToken = new EmptyToken();

    public Player()
    {
        this.token = null;
    }

    public Player(PlayerToken token) 
    {
        this.assignToken(token);
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
        return gold.amount();
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
                        gold.gain(amount * pickaxe.gainFactor());
                    })
                    .ifBroken(() ->
                    {
                        gold.gain(amount);
                        pickaxeToken = new EmptyToken();
                    })
                    .ifIdle(() -> 
                    {
                        gold.gain(amount);
                    });
            } else 
            {
                gold.gain(amount);
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
