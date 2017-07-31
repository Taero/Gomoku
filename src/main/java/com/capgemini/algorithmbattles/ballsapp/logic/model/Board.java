package com.capgemini.algorithmbattles.ballsapp.logic.model;

import com.capgemini.algorithmbattles.ballsapp.logic.BoardDrawer;

public class Board {

  private static final int SIZE = 10;
  private Player[][] board = new Player[SIZE][SIZE];

  public void placeMove(BoardCell move) {
    board[move.getX()][move.getY()] = move.getPlayer();
  }

  public BoardCell getFirstEmptyCell() {
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (board[i][j] == null) {
          return new BoardCell(i, j, null);
        }
      }
    }
    return null;
  }

  public boolean isGameFinished() {
    // TODO: Please implement it.
    return false;
  }

  public void printBoard() {
    BoardDrawer.drawBoard(this.board);
  }

    //zwraca dowolne pole, zajęte przez danego gracza
    public BoardCell getFirstPlayerBall(Player player) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == player) {
                    return new BoardCell(i, j, player);
                }
            }
        }
        return null;
    }

    public BoardCell getEnemyAdjacentPosition(BoardCell myCell) {
      Player enemy = myCell.getPlayer() == Player.PLAYER_1 ? Player.PLAYER_2 : Player.PLAYER_1;
        for (int i = myCell.getX() > 0 ? myCell.getX() - 1 : 0; i <= myCell.getX() + 1; i++) {
            for (int j = myCell.getY() > 0 ? myCell.getY() - 1 : 0; j <= myCell.getY() + 1; j++) {
                if (board[i][j] == enemy) {
                    return new BoardCell(i, j, enemy);
                }
            }
        }
        return null;
    }

    public boolean isEmptyCell(int x, int y) {
        return board[x][y] == null;
    }

    public Player getCellValue(int x, int y) {
        return board[x][y];
    }

}
