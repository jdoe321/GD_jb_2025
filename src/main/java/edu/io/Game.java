package edu.io;

import edu.io.token.*;

public class Game
{
    public Board board;
    public Player player;

    public Game()
    {
        board = new Board();
    }

    public void join(Player player)
    {
        this.player = player;
        PlayerToken token = new PlayerToken(board);
        player.assignToken(token);
    }

    public void start()
    {
        //do testow:
        GoldToken gold1 = new GoldToken(25.0);
        board.placeToken(2, 2, gold1);
        GoldToken gold2 = new GoldToken(50.0);
        board.placeToken(4, 4, gold2);

        PickaxeToken pickaxe = new PickaxeToken();
        board.placeToken(1, 3, pickaxe);

        while (true)
        {
            board.display();

            System.out.println("Wpisz W A S D do poruszania.");
            System.out.println("");
            try {
                int key = System.in.read();
                System.in.read(); // Ignorowanie znaku nowej linii
                switch (key) {
                    case 'W':
                    case 'w':
                        player.token.move(PlayerToken.Move.UP);
                        break;
                    case 'S':
                    case 's':
                        player.token.move(PlayerToken.Move.DOWN);
                        break;
                    case 'A':
                    case 'a':
                        player.token.move(PlayerToken.Move.LEFT);
                        break;
                    case 'D':
                    case 'd':
                        player.token.move(PlayerToken.Move.RIGHT);
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.println("");
            System.out.println("");
        }

    }
}
