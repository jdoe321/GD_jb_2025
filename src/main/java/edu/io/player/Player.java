package edu.io.player;

import edu.io.token.*;
import edu.io.player.Gold;

public class Player 
{
    public PlayerToken token;
    private final Gold gold = new Gold(0.0);
    private Shed shed = new Shed();

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
        switch (token)
        {
            case GoldToken goldToken ->
            {
                double amount = (goldToken).amount();
                Tool tool = shed.getTool();

                if (tool instanceof PickaxeToken pickaxe)  //TODO: fixme
                {   
                    pickaxe.useWith(goldToken)
                        .ifWorking(() ->
                        {
                            gold.gain(amount * pickaxe.gainFactor());
                        })
                        .ifBroken(() ->
                        {
                            gold.gain(amount);
                            shed.dropTool();
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
            case PickaxeToken pickaxeToken ->
            {
                shed.add(pickaxeToken);
                System.out.println("Podniesiony kilof");
            }
            case AnvilToken anvilToken ->
            {
                Tool tool = shed.getTool();
                if (tool instanceof PickaxeToken pickaxe) 
                {
                    pickaxe.repair();
                    System.out.println("naprawione");
                } else 
                {
                    System.out.println("Brak kilofa");
                }
            }
            default ->
            {
                // Nieznany token,
            }
        }
    }
}
