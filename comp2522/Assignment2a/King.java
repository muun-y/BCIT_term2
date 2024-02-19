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
        return true; 
    }
}
