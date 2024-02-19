import javax.swing.JOptionPane;
import java.awt.*;

/**
 * Comp2522 Assignment2C.
 * A concrete implementation of a 2D board that extends the abstract Board class.
 * This class represents a two-dimensional game board with a grid of tiles and
 * implements all the abstract methods defined in the Board class.
 * 
 * It provides functionality to initialize the board, handle tile clicks, move pieces,
 * change player turns, and reset tiles. It also includes the option to clear all pieces
 * from the board, which can be specified upon creation of the board.
 * 
 * @author Mun Young Cho
 */
public class Board2D extends Board {
    private boolean initializePieces;

    /**
     * Constructs a new 2D board and initializes it.
     * 
     * @param initializePieces if true, pieces are placed on the board; if false, the board starts empty
     */
    public Board2D(boolean initializePieces) {
        super();
        this.initializePieces = initializePieces;
        initializeBoard();
        if (!this.initializePieces) {
            clearPieces();
        }
        
        
    }

     /**
     * Clears the pieces from the second and third rows of the board.
     */
    private void clearPieces() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j].setPiece(null); 
            }
        }
    }

     /**
     * Initializes the board with a checkerboard pattern of tiles and places pieces if required.
     */
     @Override
     public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Color tileColor = (i + j) % 2 == 0 ? Color.BLACK : Color.WHITE;
                tiles[i][j] = new Tile(tileColor, i, j);
                this.add(tiles[i][j]);
            }
        }
        initializePieces();
    }


     /**
     * Retrieves the tile at the specified row and column indices.
     * 
     * @param row the row index of the tile
     * @param column the column index of the tile
     * @return the tile at the specified location, or null if the indices are out of bounds
     */
    @Override
    public Tile getTile(int row, int column) {
        if (row >= 0 && row < SIZE && column >= 0 && column < SIZE) {
            System.out.println("row: " + row + " column: " + column);
            return tiles[row][column];
        } else {
            // Optionally handle the error condition here, such as throwing an exception
            // For now, returning null to indicate an invalid tile request
            return null;
        }
    }

    /**
     * Handles actions to be taken when a tile is clicked, including selecting a piece,
     * moving a piece, and changing player turns.
     * 
     * @param clickedTile the tile that was clicked
     */
    @Override
    public void tileClicked(Tile clickedTile) {
        // No piece selected yet, so select this tile's piece if it has one
        if (selectedTile == null) {
            if (clickedTile.getPiece() != null) {
                if (clickedTile.getPiece().getColor().equals(currentPlayer)) {
                    // A piece was already selected, try to move it
                    selectedTile = clickedTile;
                    selectedTile.setBackground(Color.ORANGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Not your turn", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            movePiece(selectedTile, clickedTile);
            // Deselect
            selectedTile = null;
            // Reset all tiles to their original color
            resetTiles();
        }
    }

     /**
     * Moves a piece from one tile to another if the move is valid.
     * 
     * @param fromTile the tile from which the piece is being moved
     * @param toTile the tile to which the piece is being moved
     */
    @Override
    public void movePiece(Tile fromTile, Tile toTile) {
        Piece piece = fromTile.getPiece();

        if (piece != null && piece.getColor().equals(currentPlayer) && piece.isValidMove(this, fromTile, toTile)) {
            // If the move is valid, then proceed with the move
            toTile.setPiece(piece);
            fromTile.setPiece(null);
            //Change the turn
            changePlayerTurn();
        } else {
            // If the move is invalid, do not move the piece
            JOptionPane.showMessageDialog(this, "Invalid move", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Changes the turn to the next player.
     */
    @Override
    public void changePlayerTurn() {
        currentPlayer = currentPlayer.equals(Color.BLUE) ? Color.RED : Color.BLUE;
    }

     /**
     * Resets the colors of all tiles to their original state.
     */
    @Override
    public void resetTiles() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.resetColor();
            }
        }
    }

    /**
     * Places pieces on the board in their initial positions.
     */
    @Override
    public void initializePieces() {
         // Initialize the black pieces
         for (int j = 0; j < SIZE; j++) {
            tiles[1][j].setPiece(new Pawn(Color.RED));
        }
        tiles[0][0].setPiece(new Rook(Color.RED));
        tiles[0][7].setPiece(new Rook(Color.RED));
        tiles[0][1].setPiece(new Bishop(Color.RED));
        tiles[0][6].setPiece(new Bishop(Color.RED));
        tiles[0][2].setPiece(new Knight(Color.RED));
        tiles[0][5].setPiece(new Knight(Color.RED));
        tiles[0][3].setPiece(new King(Color.RED));
        tiles[0][4].setPiece(new Queen(Color.RED));

        // Initialize the white pieces
        for (int j = 0; j < SIZE; j++) {
            tiles[6][j].setPiece(new Pawn(Color.BLUE));
        }
        tiles[7][0].setPiece(new Rook(Color.BLUE));
        tiles[7][7].setPiece(new Rook(Color.BLUE));
        tiles[7][1].setPiece(new Bishop(Color.BLUE));
        tiles[7][6].setPiece(new Bishop(Color.BLUE));
        tiles[7][2].setPiece(new Knight(Color.BLUE));
        tiles[7][5].setPiece(new Knight(Color.BLUE));
        tiles[7][3].setPiece(new King(Color.BLUE));
        tiles[7][4].setPiece(new Queen(Color.BLUE));
    }

    /**
     * Resets the colors of all tiles except the specified selected tile.
     * 
     * @param selectedTile the tile that should not be reset
     */
    @Override
    public void resetTilesExcept(Tile selectedTile) {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                if (tile != selectedTile) {
                    tile.resetColor();
                }
            }
        }
    }
    }

