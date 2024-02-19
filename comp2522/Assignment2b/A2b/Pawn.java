import java.awt.Color;
import javax.swing.JOptionPane;


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

// Getter method for hasMoved
public boolean hasMoved() {
    return hasMoved;
}

// Setter method for hasMoved
public void setHasMoved(boolean hasMoved) {
    this.hasMoved = hasMoved;
}

    public boolean isValidMove(Board board, Tile start, Tile end) {
    // Assuming forward is in the negative row direction for simplicity
    int startRow = start.getRow();
    int endRow = end.getRow();
    int column = start.getColumn();
    if (!hasMoved) {
        // Allow moving 1 or 2 spaces forward on first move
        if ((startRow - endRow == 1 || startRow - endRow == 2) && column == end.getColumn()) {
            hasMoved = true; // The pawn has moved after this move is executed
            return true;
        }
    } else {
        // Allow moving only 1 space forward after the first move
        if (startRow - endRow == 1 && column == end.getColumn()) {
            return true;
        }
    }
    return false;
}

// And update the movePiece method in the Board class
private void movePiece(Tile fromTile, Tile toTile) {
    Piece piece = fromTile.getPiece();
    if (piece != null && piece.isValidMove(this, fromTile, toTile)) {
        // Move the piece
        toTile.setPiece(piece);
        fromTile.setPiece(null);
        // If the piece is a Pawn, update its hasMoved status
        if (piece instanceof Pawn) {
            ((Pawn) piece).setHasMoved(true);
        }
    } else {
        // Invalid move handling
        JOptionPane.showMessageDialog(this, "Invalid move", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
}