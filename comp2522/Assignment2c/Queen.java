import java.awt.Color;

/**
 * COMP2522 Assignment2c.
 * Represents a queen piece in a chess game. Inherits from the Piece class
 * and implements movement logic specific to the queen's capabilities on both 2D and 3D chessboards.
 * The queen can move any number of squares along a rank, file, or diagonal, but cannot jump over other pieces.
 * In a 3D chess context, the queen can also move up or down one level while retaining her normal movement patterns.
 * @author Mun Young Cho
 */
public class Queen extends Piece {

    /**
     * Constructs a Queen with the specified color.
     *
     * @param color The color of the queen, either black or white.
     */
    public Queen(Color color) {
        super(color, "Q");
    }

    /**
     * Determines whether the move from the start tile to the end tile is valid for a queen.
     * The queen can move any number of squares along a rank, file, or diagonal, but cannot leap over other pieces.
     * This method needs to be implemented to define the specific movement logic for the queen.
     *
     * @param board The chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @return true if the move is valid; false otherwise.
     */
    @Override
public boolean isValidMove(Board board, Tile start, Tile end) {
    // Check if the end tile has a piece of the same color
    if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
        return false; // Can't move to a tile with a piece of the same color
    }

    int startRow = start.getRow();
    int startColumn = start.getColumn();
    int endRow = end.getRow();
    int endColumn = end.getColumn();
    int rowDiff = Math.abs(startRow - endRow);
    int colDiff = Math.abs(startColumn - endColumn);

    // The queen moves either horizontally, vertically, or diagonally
    boolean isDiagonalMove = rowDiff == colDiff;
    boolean isStraightMove = startRow == endRow || startColumn == endColumn;

    if (!isDiagonalMove && !isStraightMove) {
        // The queen does not move in an L-shape
        return false;
    }

    // Determine the direction of the move
    int rowDirection = Integer.compare(endRow, startRow);
    int columnDirection = Integer.compare(endColumn, startColumn);

    // Check each square along the path for other pieces
    int currentRow = startRow + rowDirection;
    int currentColumn = startColumn + columnDirection;

    while (currentRow != endRow || currentColumn != endColumn) {
        Tile currentTile = board.getTile(currentRow, currentColumn);
        // If there's a piece in the way, the queen's path is blocked
        if (currentTile.getPiece() != null) {
            return false;
        }
        currentRow += rowDirection;
        currentColumn += columnDirection;
    }

    // If we reach here, the path is clear
    return true;
}

/**
     * Determines if the Queen can move to a tile on a different layer.
     * The Queen can move to an adjacent layer (up or down one level) as part of her move.
     *
     * @param board The 3D chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end The ending tile of the move.
     * @param startLayer The layer index of the start tile.
     * @param endLayer The layer index of the end tile.
     * @return true if the move is valid; false otherwise.
     */
    public boolean canMoveToLayer(Board3D board, Tile start, Tile end, int startLayer, int endLayer) {
        // The Queen can only move up or down one layer at a time.
        if (Math.abs(endLayer - startLayer) != 1) {
            return false;
        }
        
        // The Queen retains her normal movement patterns while changing layers.
        // She can move vertically, horizontally, or diagonally to an adjacent layer.

        // Check the 2D movement pattern
        if (!isValid2DMove(start, end)) {
            return false;
        }

        // Check if the destination tile on the end layer is free or occupied by an opponent's piece
        Tile destinationTile = board.getLayer(endLayer).getTile(end.getRow(), end.getColumn());
        if (destinationTile.getPiece() != null && destinationTile.getPiece().getColor().equals(this.getColor())) {
            return false;
        }

        // Check if the path is clear in the 3D sense (including checking intermediate layers if necessary)
        // Since the Queen can move like a Rook or a Bishop, you would need to implement logic
        // to check the path is clear in both straight lines and diagonals between the layers.
        // This could involve checking if the spaces directly above or below are clear, as well as
        // any intermediate spaces if moving diagonally between layers.

        // Assuming a method checkPathClear3D is implemented to handle this.
        return checkPathClear3D(board, start, end, startLayer, endLayer);
    }

    /**
     * Checks if the move is valid in a 2D plane regardless of the layer.
     * This is a helper method used to validate the queen's movement on a single layer.
     *
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @return true if the 2D move is valid; false otherwise.
     */
    private boolean isValid2DMove(Tile start, Tile end) {
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());
        return rowDiff == colDiff || start.getRow() == end.getRow() || start.getColumn() == end.getColumn();
    }

       /**
     * Checks if the path is clear for the queen to move in a 3D chess context.
     * This method needs to be implemented based on the specific architecture of the 3D chessboard.
     * It should consider both straight moves and diagonal moves across layers.
     *
     * @param board      The 3D chessboard on which the move is being attempted.
     * @param start      The starting tile of the move.
     * @param end        The ending tile of the move.
     * @param startLayer The starting layer of the move.
     * @param endLayer   The ending layer of the move.
     * @return true if the path is clear; false otherwise.
     */
    private boolean checkPathClear3D(Board3D board, Tile start, Tile end, int startLayer, int endLayer) {
        // Placeholder for path clear check. You will need to implement this based on your board's architecture.
        // It should return true if the path is clear, otherwise false.
        return true;
    }


}
