import java.awt.Color;

/**
 * COMP2522 Assignment2C.
 * Abstract representation of a chess piece. This class encapsulates the common attributes and behaviors
 * that all chess pieces share, such as color and name. It provides the foundation for piece behavior on a
 * chessboard, including the mechanisms to determine valid moves, which are left to be implemented by the
 * concrete subclasses.
 * 
 * This abstract class requires subclasses to implement methods for validating moves on a standard chessboard
 * as well as on a 3D variant of a chessboard, accommodating the additional complexity of an extra dimension.
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

    /**
     * Abstract method to determine if a piece can move to a tile on a different layer in a 3D chess context.
     * This method must be implemented by subclasses to account for the additional dimension of movement.
     *
     * @param board      The 3D chessboard on which the move is being attempted.
     * @param start      The starting tile of the move.
     * @param end        The ending tile of the move.
     * @param startLayer The layer index of the starting tile.
     * @param endLayer   The layer index of the ending tile.
     * @return true if the move to a different layer is valid for the piece; false otherwise.
     */
    public abstract boolean canMoveToLayer(Board3D board, Tile start, Tile end, int startLayer, int endLayer); 
}
