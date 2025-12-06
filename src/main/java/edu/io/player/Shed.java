package edu.io.player;

import java.util.Stack;

import edu.io.token.NoTool;
import edu.io.token.Tool;

public class Shed
{
    private Stack<Tool> tools;

    public Shed()
    {
        tools = new Stack<>();
    }

    public boolean isEmpty()
    {
        return tools.isEmpty();
    }

    public void add(Tool tool)
    {
        tools.push(tool);
    }

    public Tool getTool()
    {
        if (tools.isEmpty())
        {
            return new NoTool();
        }
        return tools.pop();
    }

    public void dropTool()
    {
        tools.pop();
    }
}
