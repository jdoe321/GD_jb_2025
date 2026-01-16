package edu.io.player;

import java.util.Stack;

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
        if (tool == null)
        {
            throw new IllegalArgumentException("Tool nie moze byc nullem");
        }

        tools.push(tool);
    }

    public Tool getTool()
    {
        if (tools.isEmpty())
        {
            return new NoTool();
        }
        return tools.peek();
    }

    public void dropTool()
    {
        tools.pop();
    }
}
