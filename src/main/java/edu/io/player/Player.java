package edu.io.player;

import edu.io.token.*;
import edu.io.player.Gold;

public class Player 
{
    public PlayerToken token;
    public Gold gold = new Gold(0.0);
    private Shed shed = new Shed();
    private Vitals vitals = new Vitals();

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
        vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE); //zawsze sie odwadnia, niezaleznie od dalszych dzialan
        switch (token)
        {
            case GoldToken goldToken ->
            {
                double amount = (goldToken).amount();
                Tool tool = shed.getTool();

                if (tool instanceof PickaxeToken pickaxe)
                {   
                    pickaxe.useWith(goldToken)
                        .ifWorking(() ->
                        {
                            gold.gain(amount * pickaxe.gainFactor());
                        })
                        .ifBroken(() ->
                        {
                            gold.gain(amount);
                            // shed.dropTool();
                        })
                        .ifIdle(() -> 
                        {
                            gold.gain(amount);
                        });
                } else 
                {
                    gold.gain(amount);
                }
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);
                // shed.add(tool); //odlozenie narzedzia z powrotem do szopy
            }
            case PickaxeToken pickaxeToken ->
            {
                shed.add(pickaxeToken);
                System.out.println("Podniesiony kilof");
            }
            case AnvilToken anvilToken ->
            {
                if ( shed.getTool() instanceof Repairable tool) 
                {
                    tool.repair();
                    vitals.dehydrate(VitalsValues.DEHYDTRAION_ANVIL);
                    System.out.println("naprawione");
                } else 
                {
                    System.out.println("Brak narzedzia do naprawienia");
                }
            }
            default ->
            {
                // Nieznany token,
            }
        }
    }

    public void displayStats() //debugowo wyswietla parametry playera
    {
        System.out.println("Hydration: " + vitals.hydration());
        System.out.println("Gold: " + gold.amount());
        Tool tool = shed.getTool();
        if (tool instanceof PickaxeToken pickaxe)
        {
            System.out.print(pickaxe.label());
            System.out.println(" - Durability: " + pickaxe.durability());
            System.out.println(" - Gain Factor: " + pickaxe.gainFactor());
            // shed.add(tool); //odlozenie narzedzia z powrotem do szopy
        } 
        else 
        {
            System.out.println("Tool: None");
        }
    }
}
