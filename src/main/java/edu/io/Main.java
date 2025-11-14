package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;

public class Main 
{
    public static void main(String[] args) 
    {
        //inicjalizacja planszy
        Board board = new Board();
        PlayerToken playerToken = new PlayerToken(board, 2, 3);
        GoldToken goldToken = new GoldToken();
        board.placeToken(4, 5, goldToken);


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
                        playerToken.move(PlayerToken.Move.UP);
                        break;
                    case 'S':
                    case 's':
                        playerToken.move(PlayerToken.Move.DOWN);
                        break;
                    case 'A':
                    case 'a':
                        playerToken.move(PlayerToken.Move.LEFT);
                        break;
                    case 'D':
                    case 'd':
                        playerToken.move(PlayerToken.Move.RIGHT);
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
