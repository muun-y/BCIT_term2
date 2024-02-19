import java.awt.Color;

/**
 * COMP2522 Assignment2b.
 * The Piece class is an abstract representation of a chess piece.
 * It holds the common properties that all chess pieces share, such as color and name.
 * The class provides a template for the behavior of the pieces on a chessboard,
 * including the ability to determine valid moves, which must be implemented by subclasses.
 *
 * @author Mun Young Cho
 */
public abstract class Piece {
    // The color of the piece
    private Color color; 
    // The name of the piece
    private String name; 

    /**
     * Constructs a chess piece with the specified color and name.
     *
     * @param color The color of the chess piece.
     * @param name  The name of the chess piece.
     */
    public Piece(Color color, String name) {
        this.color = color;
        this.name = name;
    }

    /**
     * Returns the color of the chess piece.
     *
     * @return The color of the piece.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the name of the chess piece.
     *
     * @return The name of the piece.
     */
    public String getName() {
        return name;
    }

    /**
     * Determines if a move from a start tile to an end tile is valid for this piece.
     * This method is abstract and must be implemented by subclasses to define the specific move logic.
     *
     * @param board The chessboard on which the move is being attempted.
     * @param start The starting tile of the move.
     * @param end   The ending tile of the move.
     * @return {@code true} if the move is valid; {@code false} otherwise.
     */
    public abstract boolean isValidMove(Board board, Tile start, Tile end);
}
