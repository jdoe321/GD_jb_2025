package edu.io;

public class Main 
{
    public static void main(String[] args) 
    {
        Board board = new Board();
        Token playerToken = new Token(board.PLAYER_TOKEN_LABEL);
        Token goldToken = new Token(board.GOLD_TOKEN_LABEL);

        board.grid[2][3] = playerToken;
        board.grid[5][6] = goldToken;

        board.display();
    }
}
