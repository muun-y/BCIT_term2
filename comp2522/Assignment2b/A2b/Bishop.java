import java.awt.Color;

/**
 * COMP2522 Assignment2a. 
 * The Bishop class represents a bishop piece in a chess game.
 * It extends the Piece class and defines the movement behavior specific to a bishop.
 * 
 * @author Mun Young Cho
 */
public class Bishop extends Piece {

    /**
     * Constructor for Bishop. Initializes a bishop with the specified color.
     *
     * @param color The color of the bishop, usually black or white.
     */
    public Bishop(Color color) {
        super(color, "B");
    }

    /**
     * Checks if the move from the start tile to the end tile is valid for a bishop.
     * The bishop can move diagonally over any number of squares.
     * This method must implement the specific movement logic for the bishop.
     *
     * @param board The chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @return true if the move is valid; false otherwise.
     */
    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        // First, check if the end tile has a piece of the same color
        if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
            return false;
        }
    
        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();
        int rowDiff = Math.abs(startRow - endRow);
        int colDiff = Math.abs(startColumn - endColumn);
    
        // Bishops move diagonally, so the number of rows and columns it moves should be equal
        if (rowDiff != colDiff) {
            return false; // Not a diagonal move
        }
    
        // Check all squares between the start and end tiles
        int rowIncrement = endRow > startRow ? 1 : -1;
        int colIncrement = endColumn > startColumn ? 1 : -1;
        
        for (int i = 1; i < rowDiff; i++) {
            Tile intermediateTile = board.getTile(startRow + i * rowIncrement, startColumn + i * colIncrement);
            if (intermediateTile.getPiece() != null) {
                return false; // The way is not clear
            }
        }
    
        // If we reach here, the way is clear and the move is diagonal
        return true;
    }
    
}
