package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board 
{
    private final int size;
    public Token[][] grid;

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

    public Coords getAvailableSquare()
    {
        for (int row = 0; row < size; row++) 
            for (int col = 0; col < size; col++) 
                if (grid[col][row] instanceof EmptyToken) 
                    return new Coords(col, row);
        
        throw new IllegalStateException("Brak miejsca.");
    }
}
