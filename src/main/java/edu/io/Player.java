package edu.io;

import edu.io.token.PlayerToken;

public class Player 
{
    public PlayerToken token;

    public Player(PlayerToken token) 
    {
        this.assignToken(token);
    }

    public void assignToken(PlayerToken token) 
    {
        this.token = token;
    }

    public PlayerToken token() 
    {
        return token;
    }
    
}
