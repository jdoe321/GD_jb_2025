package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

public class Main 
{
    public static void main(String[] args) 
    {
        Board board = new Board();
        PlayerToken playerToken = new PlayerToken();
        GoldToken goldToken = new GoldToken();

        board.grid[2][3] = playerToken;
        board.grid[5][6] = goldToken;

        board.display();
    }
}
