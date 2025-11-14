package edu.io;

public class Board 
{
    public final int size;
    public Token[][] grid;

    final String EMPTY_TOKEN_LABEL = "・";
    final String PLAYER_TOKEN_LABEL = "웃";
    final String GOLD_TOKEN_LABEL = "💰︎";
    
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

    public void clean()
    {
        Token t = new Token(EMPTY_TOKEN_LABEL);
        for (int row = 0; row < size; row++) 
            for (int col = 0; col < size; col++) 
                grid[col][row] = t;
    }

    public void placeToken(int col, int row, Token token)
    {
        grid[col][row] = token;
    }

    public Token square(int col, int row)
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
}
