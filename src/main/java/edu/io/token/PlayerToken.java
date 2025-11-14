package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token 
{
    public enum Move{ NONE, LEFT, RIGHT, UP, DOWN }

    private final Board board;
    private int col;
    private int row;

    public PlayerToken(Board board, int col, int row) 
    {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = col;
        this.row = row;

        board.placeToken(col, row, this);
    }

    public PlayerToken(Board board) 
    {
        this(board, 0, 0);
    }

    public void move(Move dir)
    {
        int newRow = row;
        int newCol = col;

        switch (dir) 
        {
            case LEFT:
                newCol--;
                break;
            case RIGHT:
                newCol++;
                break;
            case UP:
                newRow--;
                break;
            case DOWN:
                newRow++;
                break;
            case NONE:
                //nic nie rob
                break;
        }

        if (newCol < 0 || newCol >= board.size() || newRow < 0 || newRow >= board.size()) 
        {
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        //przypisanie nowej pozycji
        board.placeToken(col, row, new EmptyToken()); //usuniecie tokenu z obecnej pozycji
        col = newCol;
        row = newRow;
        board.placeToken(col, row, this); //ustawienie tokenu na nowej pozycji
    }

    public Board.Coords pos()
    {
        return new Board.Coords(col, row);
    }

}
