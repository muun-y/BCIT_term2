import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * COMP2522 Assignment2a. 
 * The Tile class represents a single tile on a chessboard.
 * It extends JPanel and contains a JLabel to display the piece,
 * if any, that occupies this tile. It also handles mouse click events to trigger tile selection.
 * 
 * @author Mun Young Cho
 */
public class Tile extends JPanel {
    private Color originalColor;
    private JLabel label;
    private Piece piece;
    private int row; 
    private int column; 

    // to keep track of the tile selection state
    private boolean isSelected = false; 

     /**
     * Constructor for {@code Tile}. Initializes the tile with a specified color.
     *
     * @param color The color of the tile.
     */
    public Tile(Color color) {
        this.originalColor = color;
        this.row = row; 
        this.column = column; 
        initTile();
    }

     /**
     * Returns the row of this tile on the chessboard.
     *
     * @return The row of the tile.
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of this tile on the chessboard.
     *
     * @return The column of the tile.
     */
    public int getColumn() {
        return column;
    }

     /**
     * Initializes the tile with its color and sets up the mouse listener.
     * The mouse listener will trigger a click event on the board to handle the selection of the tile.
     */
    private void initTile() {
        setBackground(originalColor);
        setLayout(new BorderLayout());
        label = new JLabel("", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        // Add mouse listener for the click event
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Container parent = getParent();
                if (parent instanceof Board) {
                    ((Board) parent).tileClicked(Tile.this);
                }
            }
        });
    }

    /**
     * Handles the selection of this tile. If the tile is clicked, the selection state is toggled,
     * and the background color is updated accordingly. It also triggers the board to reset other tiles.
     */
    public void handleTileSelection() {
        Container parent = getParent();
        if (parent instanceof Board) {
            ((Board) parent).resetTilesExcept(this);
            isSelected = !isSelected; // Toggle selection state
            setBackground(isSelected ? Color.ORANGE : originalColor);
        }
    }

    /**
     * Sets the chess piece on this tile and updates the label to display the piece's name.
     *
     * @param piece The chess piece to place on this tile.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
        updateLabel();
    }

     /**
     * Updates the label with the piece's name and color.
     * If there is no piece on the tile, the label is cleared.
     */
    private void updateLabel() {
        if (piece != null) {
            label.setText(piece.getName());
            label.setForeground(piece.getColor());
        } else {
            label.setText("");
        }
    }

    /**
     * Returns the chess piece on this tile.
     *
     * @return The Piece object on this tile, or null if the tile is empty.
     */
    public Piece getPiece() {
        return piece;
    }
    
    /**
     * Resets the tile's color to its original state and clears the selection state.
     */
    public void resetColor() {
        setBackground(originalColor);
        isSelected = false; // Reset the selection state
    }
}
