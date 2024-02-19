import java.awt.Color;

/**
 * Comp2522 Assignment2c.
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

    /**
     * Determines if the King can move to a tile on a different layer.
     * The King can move to an adjacent layer (up or down one level) to a tile that
     * is directly above or below it, reflecting a move of one "space".
     *
     * @param board      The 3D chessboard on which the move is being attempted.
     * @param start      The starting tile of the move.
     * @param end        The ending tile of the move.
     * @param startLayer The layer index of the start tile.
     * @param endLayer   The layer index of the end tile.
     * @return true if the move is valid; false otherwise.
     */
    public boolean canMoveToLayer(Board3D board, Tile start, Tile end, int startLayer, int endLayer) {
        // The king can only move up or down one layer at a time.
        if (Math.abs(endLayer - startLayer) != 1) {
            return false;
        }
        
        // The king's movement on the 2D plane should be one square in any direction,
        // including staying in the same square when moving between layers.
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());

        // Check that the destination tile is either the same or immediately adjacent.
        if (rowDiff <= 1 && colDiff <= 1) {
            // Check if the destination tile is empty or occupied by an opponent's piece.
            Tile destinationTile = board.getLayer(endLayer).getTile(end.getRow(), end.getColumn());
            if (destinationTile.getPiece() == null || !destinationTile.getPiece().getColor().equals(this.getColor())) {
                return true; // The move is valid.
            }
        }

        return false; // The move is not valid.
    }
    
}
