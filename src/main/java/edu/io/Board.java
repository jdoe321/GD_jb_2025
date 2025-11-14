package edu.io;

public class Board 
{
    public int size; //TODO:enkapsulacja
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
        for (int row = 0; row < size; row++) 
            for (int col = 0; col < size; col++) 
                grid[col][row] = new Token(EMPTY_TOKEN_LABEL);
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
        System.out.println("Display placeholder"); //TODO: zrobic wyswietlanie
    }

}
