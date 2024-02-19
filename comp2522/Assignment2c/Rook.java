import java.awt.Color;

/**
 * COMP2522 Assignment2c.
 * The Rook class represents the rook piece in a chess game.
 * It extends the Piece class and encapsulates the movement logic specific to the rook.
 * @author Mun Young Cho
 */
public class Rook extends Piece {

    /**
     * Constructs a Rook with the specified color.
     *
     * @param color The color of the rook, either black or white.
     */
    public Rook(Color color) {
        super(color, "R");
    }

    /**
     * Checks if the move from the starting tile to the ending tile is valid for a rook.
     * This method overrides the isValidMove method in the Piece superclass.
     * It ensures that the rook moves in a straight line along ranks or files without jumping
     * over other pieces and also checks if the end tile is occupied by a piece of the same color.
     *
     * @param board The chessboard on which the move is being attempted.
     * @param start The tile from which the rook is moving.
     * @param end   The tile to which the rook is moving.
     * @return true if the move is valid according to the rook's movement rules, false otherwise.
     */
    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        // Check if the destination tile has a piece of the same color
        if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
            return false; // Can't capture your own piece
        }
    
        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();
    
        // Rook moves straight along a rank or file
        boolean isRankMove = startRow == endRow;
        boolean isFileMove = startColumn == endColumn;
    
        // The move is valid if it's either a rank move or a file move, but not both or neither
        if (!(isRankMove ^ isFileMove)) {
            return false;
        }
    
        // Determine the direction of the move
        int rowDirection = Integer.compare(endRow, startRow);
        int columnDirection = Integer.compare(endColumn, startColumn);
    
        // Check each square along the path for other pieces
        int currentRow = startRow;
        int currentColumn = startColumn;
    
        while ((currentRow != endRow) || (currentColumn != endColumn)) {
            // Move to the next square in the direction of the move
            currentRow += rowDirection;
            currentColumn += columnDirection;
    
            // Avoid checking the end tile itself for a piece
            if (currentRow == endRow && currentColumn == endColumn) {
                break;
            }
    
            Tile currentTile = board.getTile(currentRow, currentColumn);
            // If there's a piece in the way, the rook's path is blocked
            if (currentTile.getPiece() != null) {
                return false;
            }
        }
    
        // If we reached this point, the path is clear
        return true;
    }

    /**
     * Determines if the Rook can move to a tile on a different layer.
     * The Rook can move to an adjacent layer (up or down one level) to a tile that
     * is directly above or below it, reflecting a move of one "space".
     *
     * @param board The 3D chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end The ending tile of the move.
     * @param startLayer The layer index of the start tile.
     * @param endLayer The layer index of the end tile.
     * @return true if the move is valid; false otherwise.
     */
    public boolean canMoveToLayer(Board3D board, Tile start, Tile end, int startLayer, int endLayer) {
        // The rook can only move up or down one layer at a time.
        if (Math.abs(endLayer - startLayer) != 1) {
            return false;
        }

        // The rook's position in terms of row and column must stay the same when moving vertically.
        if (start.getRow() != end.getRow() || start.getColumn() != end.getColumn()) {
            return false;
        }

        // Check if the destination tile on the end layer is empty or has an opponent's piece.
        Tile destinationTile = board.getLayer(endLayer).getTile(end.getRow(), end.getColumn());
        if (destinationTile.getPiece() != null && destinationTile.getPiece().getColor().equals(this.getColor())) {
            return false; // Cannot move to a tile occupied by a piece of the same color.
        }

        // The move is valid if the destination tile is empty or contains an opponent's piece.
        return true;
    }
    
}
