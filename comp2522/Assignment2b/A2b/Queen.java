import java.awt.Color;

/**
 * COMP2522 Assignment2a.
 * The Queen class represents the queen piece in a chess game.
 * It extends the Piece class and contains the movement logic specific to the queen.
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

}
