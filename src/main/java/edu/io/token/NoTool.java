package edu.io.token;

public class NoTool implements Tool
{

    @Override
    public Tool useWith(Token token) 
    {
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) 
    {
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) 
    {
        action.run();
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) 
    {
        return this;
    }

    @Override
    public boolean isBroken() 
    {
        return false;
    }
    
}
