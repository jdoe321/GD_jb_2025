package edu.io;

public class Main 
{
    public static void main(String[] args) 
    {
        Board board = new Board();
        Token playerToken = new Token(Label.PLAYER_TOKEN_LABEL);
        Token goldToken = new Token(Label.GOLD_TOKEN_LABEL);

        board.grid[2][3] = playerToken;
        board.grid[5][6] = goldToken;

        board.display();
    }
}
