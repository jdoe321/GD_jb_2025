package edu.io.token;

import edu.io.Board;
import edu.io.Player;

public class PlayerToken extends Token 
{

    private Player player;
    public enum Move{ NONE, LEFT, RIGHT, UP, DOWN }

    private final Board board;
    private int col;
    private int row;

    public PlayerToken(Board board)
    {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        Board.Coords coords = board.getAvailableSquare();
        this.col = coords.col();
        this.row = coords.row();

        board.placeToken(col, row, this);
    }

    public PlayerToken(Board board, int col, int row) 
    {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = col;
        this.row = row;

        board.placeToken(col, row, this);
    }

    // Alias dla Move
    public static Move Move = PlayerToken.Move.NONE;


    public void move(Move dir)
    {
        int newRow = row;
        int newCol = col;

        switch (dir) 
        {
            case Move.LEFT:
                newCol--;
                break;
            case Move.RIGHT:
                newCol++;
                break;
            case Move.UP:
                newRow--;
                break;
            case Move.DOWN:
                newRow++;
                break;
            case Move.NONE:
                //nic nie rob
                break;
        }

        if (newCol < 0 || newCol >= board.size() || newRow < 0 || newRow >= board.size()) 
        {
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        Token tokenAtNewPos = board.peekToken(newCol, newRow);
        if (tokenAtNewPos instanceof GoldToken) 
        {
            player.gainGold(((GoldToken)tokenAtNewPos).amount() );
            System.out.println("Gold collected: " + player.gold());
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

    public void assignToPlayer(Player player) 
    {
        this.player = player;
    }

}
