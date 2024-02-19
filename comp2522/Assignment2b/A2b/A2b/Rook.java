import java.awt.Color;

/**
 * COMP2522 Assignment2b.
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
    
}
