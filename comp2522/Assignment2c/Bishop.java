import java.awt.Color;

/**
 * COMP2522 Assignment2c. 
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

    /**
     * Checks if the move from the start tile to the end tile, across layers, is valid for a bishop.
     * The bishop can move diagonally over any number of squares and can move up or down one layer,
     * following a "stairs" pattern. The bishop cannot move straight up or down between layers.
     *
     * @param board The 3D chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @param startLayer The layer index of the start tile.
     * @param endLayer   The layer index of the end tile.
     * @return true if the move is valid; false otherwise.
     */
    public boolean canMoveToLayer(Board3D board, Tile start, Tile end, int startLayer, int endLayer) {
        // The bishop can only move up or down one layer at a time
        if (Math.abs(endLayer - startLayer) != 1) {
            return false;
        }

        // Ensure the move is still a valid diagonal move on the 2D plane
        int rowDiff = Math.abs(start.getRow() - end.getRow());
        int colDiff = Math.abs(start.getColumn() - end.getColumn());
        if (rowDiff != colDiff) {
            return false;
        }

        // Check if the path is clear on the start layer
        if (!isPathClearOnLayer(board.getLayer(startLayer), start, end)) {
            return false;
        }

        // Check if the destination tile on the end layer is free or occupied by an opponent's piece
        Tile destinationTile = board.getLayer(endLayer).getTile(end.getRow(), end.getColumn());
        if (destinationTile.getPiece() != null && destinationTile.getPiece().getColor().equals(this.getColor())) {
            return false;
        }

        // If all checks pass, the move is valid
        return true;
    }

    private boolean isPathClearOnLayer(Board2D layer, Tile start, Tile end) {
        // Check if all intermediate tiles on the path are clear on the same layer
        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();
        int rowIncrement = endRow > startRow ? 1 : -1;
        int colIncrement = endColumn > startColumn ? 1 : -1;
        int rowDiff = Math.abs(startRow - endRow);

        for (int i = 1; i < rowDiff; i++) {
            Tile intermediateTile = layer.getTile(startRow + i * rowIncrement, startColumn + i * colIncrement);
            if (intermediateTile.getPiece() != null) {
                return false; // The way is not clear
            }
        }
        return true;
    }

    
}
