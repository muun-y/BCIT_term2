import java.awt.Color;

/**
 * COMP2522 Assignment2a.
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
     * Determines whether the move from the start tile to the end tile is valid for a rook.
     * Rooks can move any number of squares along a rank or file, but cannot leap over other pieces.
     * This method needs to be implemented to define the specific movement logic for the rook.
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
