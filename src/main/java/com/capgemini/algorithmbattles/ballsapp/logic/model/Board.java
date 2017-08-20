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

  public void printBoard() {
    BoardDrawer.drawBoard(this.board);
  }

    //zwraca dowolne pole, zajęte przez danego gracza
    public BoardCell getAnyPlayerBall(Player player) {
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

    public boolean isGameFinished() {
        // TODO: Please implement it.
    	Line line;
    	line = findHorizontalLine(null,5);  	//sprawdź w poziomie całość
    	if (line.getLength() == 0) {        	//sprawdź w pionie całość
        	line = findVerticalLine(null,5);    		
    	}
    	if (line.getLength() == 0) {        	//sprawdź w ukosie 1
    		line = findDLDiagonalLine(null,5);
    	}
    	if (line.getLength() == 0) {        	//sprawdź w ukosie 2
    		line = findDRDiagonalLine(null,5);
    	}
        return line.getLength() == 5;
      }
    
    Line findHorizontalLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.HORIZONTAL);
    	if (minimumLength < 2) {
    		minimumLength = 2;
    	}
    	if (forPlayer == null) {
    		line = searchHorizontalLine(Player.PLAYER_1, minimumLength);
    		if (line == null) {
    			line =  searchHorizontalLine(Player.PLAYER_2, minimumLength);
    		}
       	} else {
       		line = searchHorizontalLine(forPlayer, minimumLength);
       	}
    	return line;
    }
    
    private Line searchHorizontalLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.HORIZONTAL);
   		int lineLength;
   		BoardCell firstCell = null;
   		//wyszukiwanie właściwe
   	    for (int y = 0; y < SIZE; y++) {
   	    	lineLength = 0;
   	      for (int x = 1; x < SIZE; x++) {
   	        if (board[x][y] == forPlayer && board[x-1][y] == forPlayer) {
   	        	if (lineLength ==0) {
   	        		firstCell = new BoardCell(x, y, forPlayer);
   	        		lineLength++;
   	        	}
   	        	lineLength++;
   	        } else {
   	        	if (lineLength >= minimumLength && lineLength > line.getLength()) {
   	        		line = new Line(firstCell, lineLength, LineTypeEnum.HORIZONTAL);
   	        	}
   	        	lineLength = 0;
   	        }
   	      }
   	    }
    	return line;
    }
    
    Line findVerticalLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.VERTICAL);
    	if (minimumLength < 2) {
    		minimumLength = 2;
    	}
    	if (forPlayer == null) {
    		line = searchVerticalLine(Player.PLAYER_1, minimumLength);
    		if (line == null) {
    			line =  searchVerticalLine(Player.PLAYER_2, minimumLength);
    		}
       	} else {
       		line = searchVerticalLine(forPlayer, minimumLength);
       	}
    	return line;
    }
    
    private Line searchVerticalLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.VERTICAL);
   		int lineLength;
   		BoardCell firstCell = null;
   		//wyszukiwanie właściwe
   	    for (int x = 0; x < SIZE; x++) {
   	    	lineLength = 0;
   	      for (int y = 1; y < SIZE; y++) {
   	        if (board[x][y] == forPlayer && board[x][y-1] == forPlayer) {
   	        	if (lineLength ==0) {
   	        		firstCell = new BoardCell(x, y, forPlayer);
   	        		lineLength++;
   	        	}
   	        	lineLength++;
   	        } else {
   	        	if (lineLength >= minimumLength && lineLength > line.getLength()) {
   	        		line = new Line(firstCell, lineLength, LineTypeEnum.VERTICAL);
   	        	}
   	        	lineLength = 0;
   	        }
   	      }
   	    }
    	return line;
    }
    
    Line findDLDiagonalLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.DIAGONAL_DL);
    	if (minimumLength < 2) {
    		minimumLength = 2;
    	}
    	if (forPlayer == null) {
    		line = searchDLLine(Player.PLAYER_1, minimumLength);
    		if (line == null) {
    			line =  searchDLLine(Player.PLAYER_2, minimumLength);
    		}
       	} else {
       		line = searchDLLine(forPlayer, minimumLength);
       	}
    	return line;
    }
    
    private Line searchDLLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.DIAGONAL_DL);
   		int lineLength;
   		BoardCell firstCell = null;
   		//wyszukiwanie właściwe
   	    for (int x = minimumLength - 1; x < SIZE; x++) {		//X
   	    	lineLength = 0;
   	      for (int y = 0; y < x; y++) {		//Y
   	        if (board[x-y][y] == forPlayer && board[x-y-1][y+1] == forPlayer) {
   	        	if (lineLength ==0) {
   	        		firstCell = new BoardCell(x-y, y, forPlayer);
   	        		lineLength++;
   	        	}
   	        	lineLength++;
   	        } else {
   	        	if (lineLength >= minimumLength && lineLength > line.getLength()) {
   	        		line = new Line(firstCell, lineLength, LineTypeEnum.DIAGONAL_DL);
   	        	}
   	        	lineLength = 0;
   	        }
   	      }
   	    }
   	    for (int y = 1; y <= SIZE - minimumLength; y++) {
   	    	lineLength = 0;
   	    	for (int x = 9; x <= y + 1; x--) {
   	    		if (board[x][y-x+9] == forPlayer && board[x-1][y-x+10] == forPlayer) {
   	   	        	if (lineLength ==0) {
   	   	        		firstCell = new BoardCell(x, y-x+9, forPlayer);
   	   	        		lineLength++;
   	   	        	}
   	   	        	lineLength++;   	    			
   	    		}
   	    		else {
   	   	        	if (lineLength >= minimumLength && lineLength > line.getLength()) {
   	   	        		line = new Line(firstCell, lineLength, LineTypeEnum.DIAGONAL_DL);
   	   	        	}
   	   	        	lineLength = 0;   	    			
   	    		}
   	    	}
   	    }
   	    
    	return line;
    }
    
    Line findDRDiagonalLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.DIAGONAL_DR);
    	if (minimumLength < 2) {
    		minimumLength = 2;
    	}
    	if (forPlayer == null) {
    		line = searchDRLine(Player.PLAYER_1, minimumLength);
    		if (line == null) {
    			line =  searchDRLine(Player.PLAYER_2, minimumLength);
    		}
       	} else {
       		line = searchDRLine(forPlayer, minimumLength);
       	}
    	return line;
    }
    
    private Line searchDRLine(Player forPlayer, int minimumLength) {
    	Line line = new Line(null, 0, LineTypeEnum.DIAGONAL_DR);
   		int lineLength;
   		BoardCell firstCell = null;

   	    for (int x = 0; x <= SIZE - minimumLength; x++) {		//X
   	    	lineLength = 0;
   	      for (int y = 0; y < SIZE-x-1; y++) {		//Y
   	        if (board[x+y][y] == forPlayer && board[x+y+1][y+1] == forPlayer) {
   	        	if (lineLength ==0) {
   	        		firstCell = new BoardCell(x+y, y, forPlayer);
   	        		lineLength++;
   	        	}
   	        	lineLength++;
   	        } else {
   	        	if (lineLength >= minimumLength && lineLength > line.getLength()) {
   	        		line = new Line(firstCell, lineLength, LineTypeEnum.DIAGONAL_DR);
   	        	}
   	        	lineLength = 0;
   	        }
   	      }
   	    }
   	    for (int y = 1; y <= SIZE - minimumLength; y++) {
   	    	lineLength = 0;
   	    	for (int x = 0; x < SIZE-y-1; x++) {
   	    		if (board[x][y + x] == forPlayer && board[x+1][y + x + 1] == forPlayer) {
   	   	        	if (lineLength ==0) {
   	   	        		firstCell = new BoardCell(x, y+x, forPlayer);
   	   	        		lineLength++;
   	   	        	}
   	   	        	lineLength++;   	    			
   	    		}
   	    		else {
   	   	        	if (lineLength >= minimumLength && lineLength > line.getLength()) {
   	   	        		line = new Line(firstCell, lineLength, LineTypeEnum.DIAGONAL_DR);
   	   	        	}
   	   	        	lineLength = 0;   	    			
   	    		}
   	    	}
   	    }
   	    
    	return line;
    }
    
}
