package com.capgemini.algorithmbattles.ballsapp.logic;

import com.capgemini.algorithmbattles.ballsapp.logic.model.Board;
import com.capgemini.algorithmbattles.ballsapp.logic.model.BoardCell;
import com.capgemini.algorithmbattles.ballsapp.logic.model.Player;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testy klasy Board
 *
 * @author Artur Lukaszczyk
 */
public class BoardTest {

    @Test
    public void shouldReturnEmptyCell() {
//        given
        Board board = new Board();
//        when
        board.placeMove(new BoardCell(1, 2, Player.PLAYER_1));
        board.placeMove(new BoardCell(2, 0, Player.PLAYER_1));
        board.placeMove(new BoardCell(3, 9, Player.PLAYER_1));
        board.placeMove(new BoardCell(4, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(5, 6, Player.PLAYER_1));
//        then
        BoardCell cell = board.getFirstEmptyCell();
        Assert.assertNotNull(cell);
        Assert.assertNull(cell.getPlayer());
        Assert.assertTrue(board.isEmptyCell(1,1));
        Assert.assertFalse(board.isEmptyCell(1,2));
        Assert.assertNull(board.getCellValue(1,1));
    }

    @Test
    public void shouldFindPlayersCell() {
        //        given
        Board board = new Board();
        BoardCell cell = new BoardCell(2,3, Player.PLAYER_1);
        //        when
        board.placeMove(new BoardCell(1, 4, Player.PLAYER_1));
        board.placeMove(cell);
        board.placeMove(new BoardCell(3, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(6, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(2, 2, Player.PLAYER_1));
        BoardCell enemy = board.getEnemyAdjacentPosition(cell);
        //        then
        Assert.assertEquals(board.getCellValue(1,4), Player.PLAYER_1);
        Assert.assertEquals(board.getCellValue(3,4), Player.PLAYER_2);
        Assert.assertNotNull(enemy);
        Assert.assertEquals(enemy.getPlayer(), Player.PLAYER_2);
    }
    
    @Test
    public void shouldFinishGame() {
        //        given
        Board board = new Board();
        //        when
        board.placeMove(new BoardCell(1, 1, Player.PLAYER_1));
        board.placeMove(new BoardCell(2, 2, Player.PLAYER_1));
        board.placeMove(new BoardCell(3, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(4, 4, Player.PLAYER_1));
        board.placeMove(new BoardCell(5, 5, Player.PLAYER_1));
        board.placeMove(new BoardCell(6, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(7, 2, Player.PLAYER_1));
        board.placeMove(new BoardCell(8, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(3, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(6, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(0, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(1, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(2, 1, Player.PLAYER_2));
        board.placeMove(new BoardCell(7, 7, Player.PLAYER_2));
        board.placeMove(new BoardCell(0, 7, Player.PLAYER_2));
        board.placeMove(new BoardCell(6, 7, Player.PLAYER_2));
        //        then
        Assert.assertTrue(board.isGameFinished());
    }
    
    @Test
    public void shouldNotFinishGame() {
        //        given
        Board board = new Board();
        //        when
        board.placeMove(new BoardCell(4, 1, Player.PLAYER_1));
        board.placeMove(new BoardCell(4, 2, Player.PLAYER_1));
        board.placeMove(new BoardCell(4, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(4, 4, Player.PLAYER_1));
        board.placeMove(new BoardCell(5, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(6, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(3, 3, Player.PLAYER_1));
        board.placeMove(new BoardCell(0, 0, Player.PLAYER_1));
        board.placeMove(new BoardCell(3, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(6, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(0, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(1, 4, Player.PLAYER_2));
        board.placeMove(new BoardCell(2, 1, Player.PLAYER_2));
        board.placeMove(new BoardCell(7, 7, Player.PLAYER_2));
        board.placeMove(new BoardCell(0, 7, Player.PLAYER_2));
        board.placeMove(new BoardCell(6, 7, Player.PLAYER_2));
        //        then
        Assert.assertFalse(board.isGameFinished());
    }
    
    //TODO - jeszcze przetestowanie znajdowania linii każdego rodzaju
}
