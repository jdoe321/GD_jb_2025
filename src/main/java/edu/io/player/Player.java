package edu.io.player;

import edu.io.token.*;

public class Player 
{
    public PlayerToken token;
    public Gold gold = new Gold(0.0);
    private Shed shed = new Shed();
    public Vitals vitals = new Vitals();

    public Player()
    {
        this.token = null;
        this.vitals.setOnDeathHandler(() -> 
        {
            System.out.println("Pelne odwodnenie.");
        });
    }

    public Player(PlayerToken token) 
    {
        this.assignToken(token);
        this.vitals.setOnDeathHandler(() -> 
        {
            System.out.println("Pelne odwodnenie.");
        });
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
        if (!vitals.isAlive()) 
        {
            throw new IllegalStateException("Niezywy gracz nie moze wchodzic w interakcje z tokenami.");
        }

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
            }
            case AnvilToken anvilToken ->
            {
                if ( shed.getTool() instanceof Repairable tool) 
                {
                    tool.repair();
                    vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);
                    System.out.println("naprawione");
                } else 
                {
                    System.out.println("Brak narzedzia do naprawienia");
                }
            }
            case WaterToken waterToken ->
            {
                int hydrationAmount = waterToken.amount();
                vitals.hydrate(hydrationAmount);
            }
            default ->
            {
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
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
