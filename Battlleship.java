package BattleShipGame;

import java.util.Random;
import java.util.Scanner;

public class BattleShipGame {
    private int[][] board = new int[8][8];
    private int[][] ships = new int[4][2];
    private int[] shot = new int[2];
    private int attempts = 0;
    private int shotHit = 0;

    public void initBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = -1;
            }
        }
    }

    public void initShips() {
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            int row, col;
            do {
                row = random.nextInt(7);
                col = random.nextInt(7);
            } while (board[row][col] != -1);
            ships[i][0] = row;
            ships[i][1] = col;
        }
    }

    public void showBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == -1) {
                    System.out.print("~ ");
                } else if (board[i][j] == 0) {
                    System.out.print("* ");
                } else if (board[i][j] == 1) {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    public void shoot(int row, int col) {
        shot[0] = row;
        shot[1] = col;
    }

    public boolean hit() {
        for (int i = 0; i < 4; i++) {
            if (ships[i][0] == shot[0] && ships[i][1] == shot[1]) {
                shotHit++;
                return true;
            }
        }
        return false;
    }

    public void hint() {
        System.out.println("You aimed at row " + (shot[0] + 1) + " and column " + (shot[1] + 1));
    }

    public void changeBoard() {
        if (hit()) {
            board[shot[0]][shot[1]] = 1;
            System.out.println("You hit a ship!");
        } else {
            board[shot[0]][shot[1]] = 0;
            System.out.println("You missed!");
        }
    }

    
    
    
    
    
    public static void main(String[] args) {
        BattleShipGame game = new BattleShipGame();
        game.initBoard();
        game.initShips();
        Scanner scanner = new Scanner(System.in);

        do {
            game.showBoard();
            System.out.print("Enter row (1-8): ");
            int row = scanner.nextInt() - 1;
            System.out.print("Enter column (1-8): ");
            int col = scanner.nextInt() - 1;
            game.shoot(row, col);
            game.hint();
            game.changeBoard();
            game.attempts++;
        } while (game.shotHit < 4);

        System.out.println("Congratulations! You sunk all the ships in " + game.attempts + " attempts.");
        scanner.close();
    }
}

