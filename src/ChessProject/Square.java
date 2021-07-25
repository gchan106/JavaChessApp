package ChessProject;

public class Square {
	
	private int row;
	private int column;
	private Piece piece;
	
	public Square(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getHorizontal() {
		return row;
	}
	
	public int getVertical() {
		return column;
	}
	
	public Piece getPiece() {
		return piece;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
}
