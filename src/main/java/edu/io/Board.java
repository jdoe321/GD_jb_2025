package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board 
{
    private final int size;
    public Token[][] grid;
    public enum PlacementStrategy{ FIRST, RANDOM }
    private PlacementStrategy placementStrategy = PlacementStrategy.FIRST;

    public record Coords(int col, int row) {}
    
    public Board(int size) 
    {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }
    
    public Board() //konstuktor domyslny
    {
        this(8);
    }

    public int size()
    {
        return size;
    }

    public void clean()
    {
        Token t = new EmptyToken();
        for (int row = 0; row < size; row++) 
            for (int col = 0; col < size; col++) 
                grid[col][row] = t;
    }

    public void placeToken(int col, int row, Token token)
    {
        grid[col][row] = token;
    }

    public Token peekToken(int col, int row)
    {
        return grid[col][row];
    }


    public void display()
    {
        for (int row = 0; row < size; row++)
        {
            for (int col = 0; col < size; col++)
            { 
                System.out.print(grid[col][row].label());
                System.out.print(" ");
            }
            System.out.println();
        }
    
    }

    public void setPlacementStrategy(PlacementStrategy strategy)
    {
        this.placementStrategy = strategy;
    }

    public PlacementStrategy getPlacementStrategy()
    {
        return this.placementStrategy;
    }

    public Coords getAvailableSquare()
    {
        if (placementStrategy == PlacementStrategy.RANDOM) 
        {
            java.util.Random rand = new java.util.Random();
            int emptyCount = 0;

            // Count empty squares
            for (int row = 0; row < size; row++) 
                for (int col = 0; col < size; col++) 
                    if (grid[col][row] instanceof EmptyToken) 
                        emptyCount++;

            if (emptyCount == 0) 
                throw new IllegalStateException("Brak miejsca.");

            // Try to find a random empty square
            int attempts = 0;
            int maxAttempts = size * size * size * size; //jakies zabezpieczenie na wszelki wypadek

            while (attempts < maxAttempts) 
            {
                int col = rand.nextInt(size);
                int row = rand.nextInt(size);

                if (grid[col][row] instanceof EmptyToken) 
                    return new Coords(col, row);

                attempts++;
            }

            // Fallback to sequential search if random attempts fail
            for (int row = 0; row < size; row++) 
                for (int col = 0; col < size; col++) 
                    if (grid[col][row] instanceof EmptyToken) 
                        return new Coords(col, row);

            throw new IllegalStateException("Brak miejsca.");
        } else // PlacementStrategy.FIRST
        {
            for (int row = 0; row < size; row++) 
                for (int col = 0; col < size; col++) 
                    if (grid[col][row] instanceof EmptyToken) 
                        return new Coords(col, row);
            
            throw new IllegalStateException("Brak miejsca.");
        }
    }
}
