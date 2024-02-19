import javax.swing.*;
import java.awt.*;

/**
 * COMP2522 Assignment2b. 
 * The Board class represents the chessboard in the chess
 * game. It extends JPanel and manages the initialization and interaction of
 * tiles within the chessboard.
 * 
 * @author Mun Young Cho
 */

public class Board extends JPanel {
    private final int SIZE = 8; // standard size of a chess board
    private Tile[][] tiles = new Tile[SIZE][SIZE];
    private Tile selectedTile;

    private Color currentPlayer;

    /**
     * Constructor for Board. Initializes the chessboard with a grid layout and sets
     * up the initial state of the board including all the tiles.
     */
    public Board() {
        this.setLayout(new GridLayout(SIZE, SIZE));
        currentPlayer = Color.BLUE;
        initializeBoard();
    }

    /**
     * Initializes the chessboard with an 8x8 grid of tiles, setting alternating
     * colors for each tile and placing the chess pieces in their initial positions.
     */
    private void initializeBoard() {
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
     * Retrieves the tile at the specified row and column.
     *
     * @param row    The row of the tile to retrieve.
     * @param column The column of the tile to retrieve.
     * @return The tile at the specified row and column.
     */
    public Tile getTile(int row, int column) {
        if (row >= 0 && row < SIZE && column >= 0 && column < SIZE) {
            return tiles[row][column];
        } else {
            // Optionally handle the error condition here, such as throwing an exception
            // For now, returning null to indicate an invalid tile request
            return null;
        }
    }

    /**
     * Handles the event when a tile on the board is clicked. If no tile is
     * currently selected and the clicked tile has a piece, it selects the tile. If
     * a tile is already selected, it attempts to move the selected piece to the
     * clicked tile.
     *
     * @param clickedTile The {@code Tile} that has been clicked.
     */
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
     * Moves a piece from the source tile to the destination tile. If the
     * destination tile has a piece, it will be captured.
     *
     * @param fromTile The tile from which the piece is being moved.
     * @param toTile   The tile to which the piece is moving.
     */
    private void movePiece(Tile fromTile, Tile toTile) {
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
     * Turn check method
     */
    private void changePlayerTurn() {
        currentPlayer = currentPlayer.equals(Color.BLUE) ? Color.RED : Color.BLUE;
    }

    /**
     * Resets the color of all tiles to their original state.
     */
    public void resetTiles() {
        for (Tile[] row : tiles) {
            for (Tile tile : row) {
                tile.resetColor();
            }
        }
    }

    /**
     * Places the initial set of chess pieces on the board.
     */
    private void initializePieces() {
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
     * Resets the color of all tiles except the selected tile.
     *
     * @param selectedTile The tile that will not have its color reset.
     */
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
