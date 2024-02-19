import java.awt.Color;

/**
 * The {@code Pawn} class represents a pawn piece in a chess game.
 * It extends the {@code Piece} class and contains the movement logic specific to a pawn.
 */
public class Pawn extends Piece {

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
     * Pawns move forward one square, but capture diagonally. On their first move, they have the option to move forward two squares.
     * This method needs to be implemented to define the specific movement logic for the pawn.
     *
     * @param board The chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @return {@code true} if the move is valid; {@code false} otherwise.
     */
    @Override
    public boolean isValidMove(Board board, Tile start, Tile end) {
        // Pawn movement logic
        // Actual movement logic for the pawn needs to be implemented here.
        return true; // Placeholder return value
    }
}
