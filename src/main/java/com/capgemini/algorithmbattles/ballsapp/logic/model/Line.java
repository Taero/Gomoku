package com.capgemini.algorithmbattles.ballsapp.logic.model;

public class Line {
private LineTypeEnum lineType;
private int length;
private BoardCell firstCell;

public Line(BoardCell firstCell, int length, LineTypeEnum lineType) {
	this.firstCell = firstCell;
	this.length = length;
	this.lineType = lineType;
}

public LineTypeEnum getLineType() {
	return lineType;
}

public int getLength() {
	return length;
}

public BoardCell getFirstCell() {
	return firstCell;
}

public BoardCell[] getCells() {
	BoardCell[] cells = new BoardCell[this.length];
	cells[0] = this.firstCell;
	for (int i=1; i<=length; i++ ) {
		switch (lineType) {
		case HORIZONTAL:
			cells[i] = new BoardCell(firstCell.getX() + i, firstCell.getY(), firstCell.getPlayer());
			break;
		case VERTICAL:
			cells[i] = new BoardCell(firstCell.getX(), firstCell.getY() + i, firstCell.getPlayer());
			break;
		case DIAGONAL_DL:
			cells[i] = new BoardCell(firstCell.getX() - i, firstCell.getY() + i, firstCell.getPlayer());
			break;
		case DIAGONAL_DR:
			cells[i] = new BoardCell(firstCell.getX() + i, firstCell.getY() + i, firstCell.getPlayer());
			break;
		}
	}
	return cells;
}


}
