import java.awt.Color;


/**
 * COMP2522 Assignment2c.
 * The Pawn class represents a pawn piece in a chess game. It extends
 * the Piece class and contains the movement logic specific to a pawn.
 * 
 * @author Mun Young Cho
 */
public class Pawn extends Piece {
    private boolean hasMoved;

    /**
     * Constructs a {@code Pawn} with the specified color.
     *
     * @param color The color of the pawn, either black or white.
     */
    public Pawn(Color color) {
        super(color, "P");
    }

    /**
     * Determines whether the move from the start tile to the end tile is valid for a pawn.
     * Pawns move differently compared to other pieces: they move one square forward but capture
     * diagonally. This method checks if such a move is valid considering the pawn's special
     * first move capability and its standard move thereafter.
     * 
     * @param board The chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @return true if the move is valid according to the pawn's rules, false otherwise.
     */
    public boolean isValidMove(Board board, Tile start, Tile end) {
        // Check if the end tile has a piece of the same color
        if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
            return false; // Can't move to a tile with a piece of the same color
        }

        int startRow = start.getRow();
        int startColumn = start.getColumn();
        int endRow = end.getRow();
        int endColumn = end.getColumn();

        // Determine the direction of the pawn (assuming white moves up and black moves down)
        int direction = this.getColor().equals(Color.BLUE) ? -1 : 1;

        // Check for a valid move in the forward direction
        if (startColumn == endColumn) {
            // Check for moving forward 1 or 2 spaces on the first move
            if (!hasMoved && (endRow == startRow + 2 * direction) && board.getTile(startRow + direction, startColumn).getPiece() == null) {
                hasMoved = true;
                return true;
            }
            // Check for moving forward 1 space after the first move
            else if ((hasMoved || !hasMoved) && endRow == startRow + direction) {
                hasMoved = true;
                return true;
            }
        }

        // Check for capturing a piece in a diagonal move
        if (Math.abs(startColumn - endColumn) == 1 && endRow == startRow + direction) {
            return end.getPiece() != null && !end.getPiece().getColor().equals(this.getColor());
        }

        return false;
    }

    /**
     * Determines if the Pawn can move vertically to a different layer as its first move.
     * A Pawn can move straight forward to the third layer on its first move.
     * It cannot capture in this way, only move to an empty space.
     *
     * @param board The 3D chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end The ending tile of the move.
     * @param startLayer The layer index of the start tile.
     * @param endLayer The layer index of the end tile.
     * @return true if the move is valid; false otherwise.
     */
    public boolean canMoveToLayer(Board3D board, Tile start, Tile end, int startLayer, int endLayer) {
        // The pawn can only move up two layers at a time on its first move.
        if (hasMoved || Math.abs(endLayer - startLayer) != 1) {
            return false;
        }
        
        // Ensure the pawn is moving straight forward to the same column and row position.
        if (start.getRow() != end.getRow() || start.getColumn() != end.getColumn()) {
            return false;
        }

        // Ensure the destination tile is empty.
        Tile destinationTile = board.getLayer(endLayer).getTile(end.getRow(), end.getColumn());
        if (destinationTile.getPiece() != null) {
            return false;
        }

        // Ensure the path is clear on the intermediate layer.
        Tile intermediateTile = board.getLayer(startLayer + 1).getTile(end.getRow(), end.getColumn());
        if (intermediateTile.getPiece() != null) {
            return false;
        }

        // The move is valid, and since this is a special move, we mark the pawn as having moved.
        hasMoved = true;
        return true;
    }


}