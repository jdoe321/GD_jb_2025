package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token 
{
    private final Board board;
    private int col;
    private int row;

    public PlayerToken(Board board, int col, int row) 
    {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = col;
        this.row = row;
    }

    public void move(int dir)
    {
        // TODO: zaimplementowanie poruszania
    }

    public Board.Coords pos()
    {
        return new Board.Coords(col, row);
    }

}
