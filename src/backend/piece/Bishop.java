package backend.piece;

import java.util.ArrayList;
import java.util.List;

import backend.Position;
import backend.Square;
import backend.game.Game;

public class Bishop extends Piece {
    public Bishop(boolean color) {
        super(color);
    }

    public String algebraicName() {
        return "B";
    }

    public String fenName() {
        return getColor() ? "B" : "b";
    }

    public List<Square> possibleMoves(Position position, Square square) {
    		List<Square> squares = new ArrayList<>();
    		squares.addAll(iterate(1,1, position, square));
    		squares.addAll(iterate(-1,1, position, square));
    		squares.addAll(iterate(1,-1, position, square));
    		squares.addAll(iterate(-1,-1, position, square));
        return squares;
    }
    private List<Square> iterate(int vertical, int horizontal, Position position, Square square){
    		List<Square> squares = new ArrayList<>();
    		int rank = square.getRank();
    	 	int file = square.getFile();
    	 	for (int i = 1; i <= 8; i++) {
    	 		if(squareOnBoard(file + (i * horizontal), rank + (i * vertical))) {
    	 			Square test = new Square(file + (i * horizontal), rank + (i * vertical));
    	 			if(position.pieceOnSquare(test)) {
    	 				if(position.getPieceOnSquare(test).getColor() != getColor()) {
    	 					squares.add(test);
    	 				}
    	 				break;
    	 			}
    	 			else {
    	 				squares.add(test);
    	 			}
    	 		}
    	 		else {
    	 			break; 
    	 		}
    	 	}
    	 	return squares;
    }

	@Override
	public List<Square> possibleMovesFull(Game game, Square square) {
		// TODO Auto-generated method stub
		return null;
	}

}