import java.awt.Color;

/**
 * COMP2522 Assignment2c.
 * The Knight class represents the knight piece in a chess game.
 * It extends the Piece class and contains the movement logic specific to the
 * knight.
 * 
 * @author Mun Young Cho
 */
public class Knight extends Piece {

    /**
     * Constructs a Knight with the specified color.
     *
     * @param color The color of the knight, either black or white.
     */
    public Knight(Color color) {
        // Typically, "N" is used to represent knights in notation to avoid confusion
        // with "K" for king.
        super(color, "N");
    }

    /**
     * Determines whether the move from the start tile to the end tile is valid for
     * a knight.
     * Knights move in an 'L' shape: they can move two squares in one direction and
     * then one square perpendicular to that.
     * This method needs to be implemented to define the specific movement logic for
     * the knight.
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

        // Check the L-shape movement pattern (2-1 or 1-2)
        return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
    }
    
    /**
     * Determines if the Knight can move to a tile on a different layer.
     * The Knight can move to an adjacent layer (up or down one level) to a tile that
     * is in the same 'L' shape pattern as it moves on a single layer.
     *
     * @param board      The 3D chessboard on which the move is being attempted.
     * @param start      The starting tile of the move.
     * @param end        The ending tile of the move.
     * @param startLayer The layer index of the start tile.
     * @param endLayer   The layer index of the end tile.
     * @return true if the move is valid; false otherwise.
     */
    public boolean canMoveToLayer(Board3D board, Tile start, Tile end, int startLayer, int endLayer) {
        // The knight can only move up or down one layer at a time.
        if (Math.abs(endLayer - startLayer) != 1) {
            return false;
        }
        
        // Check if the movement is still an L-shaped move on the 2D plane
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());

        // Validate the L-shaped pattern (2-1 or 1-2)
        boolean isValid2DMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        // Check if the destination tile on the end layer is free or occupied by an opponent's piece
        if (isValid2DMove) {
            Tile destinationTile = board.getLayer(endLayer).getTile(end.getRow(), end.getColumn());
            return destinationTile.getPiece() == null || !destinationTile.getPiece().getColor().equals(this.getColor());
        }

        return false; // The move is not valid for other cases
    }
}
