package ChessProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Redo {
	
	private ArrayList<Square> redoList = new ArrayList<Square>();
	private Map<Integer, Piece> redoPiece = new HashMap<Integer,Piece>();
	private Piece undoPiece;
	
	public void recordMove(Square current, Square next, int turn, Board b) {
		Piece currentPiece = current.getPiece();
		
		if(current.getPiece().getPlayer() == turn && current.getHorizontal() != next.getHorizontal() ||
				current.getVertical() != next.getVertical()) {
			
			redoList.add(b.getPosition(current.getHorizontal(), current.getVertical())); //Record the position

			redoPiece.put(turn, currentPiece); //Record what piece and who owned it.			
		}
	}
	public void undomove(Square current, Square next, int turn, Board b) {
		if(!redoList.isEmpty() && !redoPiece.isEmpty()) {
			undoPiece = redoPiece.get(turn); //Get the piece
			Square history = redoList.get(redoList.size()-1); //Get the position
			history.setPiece(undoPiece); //Set the piece
		}
	}
	}
	
	


