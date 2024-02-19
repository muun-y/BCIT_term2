import java.awt.Color;

/**
 * Comp2522 Assignment2a.
 * The King class represents the king piece in a chess game.
 * It extends the Piece class and encapsulates the movement logic specific to the king.
 * 
 * @author Mun Young Cho
 */
public class King extends Piece {

    /**
     * Constructor for King. Initializes a king with the specified color.
     *
     * @param color The color of the king, either black or white.
     */
    public King(Color color) {
        super(color, "K");
    }

    /**
     * Checks if the move from the start tile to the end tile is valid for the king.
     * The king can move exactly one square horizontally, vertically, or diagonally.
     * This method must implement the specific movement logic for the king.
     *
     * @param board The chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @return true if the move is valid; false otherwise.
     */
    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        // Check if the destination tile has a piece of the same color
        if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
            return false; // Can't move to a tile occupied by a piece of the same color
        }
    
        // Calculate the differences in row and column between the start and end tiles
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());
    
        // The king can move one square in any direction
        // This means the absolute difference for both row and column should be at most 1
        if (rowDiff <= 1 && colDiff <= 1) {
            // The move is valid if the king moves to a different tile
            // and does not stay in the same place
            return rowDiff + colDiff > 0;
        }
    
        // If the move is more than one square away, it's invalid for the king
        return false;
    }
    
}
