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
        return true; 
    }
}
