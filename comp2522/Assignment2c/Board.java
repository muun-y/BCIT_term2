import javax.swing.*;
import java.awt.*;

/**
 *COMP2522 Assignment2C.
 * Abstract class representing the basic structure of a game board.
 * This class provides a skeletal implementation of a board game that is composed
 * of a grid of tiles and maintains the state of the current player.
 * 
 * The board is initialized with a specified size, layout, and starting player.
 * Subclasses should implement the abstract methods to define the specific
 * game logic, tile interaction, piece movement, and player turn mechanism.
 * 
 * @author  Mun Young Cho. 
 */
public abstract class Board extends JPanel {
    public final int SIZE = 8; 
    public Tile[][] tiles; 
    public Tile selectedTile;
    public Color currentPlayer; 

     /**
     * Constructor for creating a new Board.
     * Initializes the board with tiles and sets the layout and initial player.
     */
    public Board() {
        tiles = new Tile[SIZE][SIZE];
        setLayout(new GridLayout(SIZE, SIZE));
        currentPlayer = Color.BLUE;
    }

    /**
     * Initializes the board with tiles and sets up the board layout.
     */
    public abstract void initializeBoard(); 

    /**
     * Retrieves the tile at the specified row and column.
     * 
     * @param row the row index of the tile
     * @param column the column index of the tile
     * @return the tile at the specified location
     */
    public abstract Tile getTile(int row, int column); 

      /**
     * Defines the action to be taken when a tile is clicked.
     * 
     * @param clickedTile the tile that was clicked
     */
    public abstract void tileClicked(Tile clickedTile); 

      /**
     * Moves a game piece from one tile to another.
     * 
     * @param fromTile the tile from which the piece is moved
     * @param toTile the tile to which the piece is moved
     */
    public abstract void movePiece(Tile fromTile, Tile toTile); 

    /**
     * Changes the turn to the next player.
     */
    public abstract void changePlayerTurn(); 

      /**
     * Resets the state of all tiles to their default state.
     */
    public abstract void resetTiles(); 

    /**
     * Initializes the pieces on the board at the beginning of the game.
     */
    public abstract void initializePieces(); 

     /**
     * Resets the state of all tiles except the specified selected tile.
     * 
     * @param selectedTile the tile that should not be reset
     */
    public abstract void resetTilesExcept(Tile selectedTile); 
}
