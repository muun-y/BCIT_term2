import java.awt.*;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * COMP2522 Assignment2C.
 * A concrete implementation of a 3D board, extending the abstract Board class.
 * This class represents a three-dimensional game board with multiple layers.
 * Each layer is an instance of Board2D, and the class manages interactions between
 * these layers, such as moving pieces within and across layers.
 * @author Mun Young Cho
 */
public class Board3D extends Board {
    public Board2D[] layers;
    public int currentLayer;

     /**
     * Constructor for Board3D. Initializes the layers and sets the current layer to 0.
     */
    public Board3D() {
        layers = new Board2D[3];

        for (int i = 0; i < layers.length; i++) {
            layers[i] = new Board2D(i == 0);
             // Add the mouse event listener to each Board2D layer
             addMouseListenerToLayer(layers[i]);
        }
        currentLayer = 0;
        initializeBoard();
    }

     /**
     * Adds a mouse listener to a given layer to handle mouse click events.
     *
     * @param layer The Board2D layer to which the mouse listener will be added.
     */
    private void addMouseListenerToLayer(Board2D layer) {
        layer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Component clickedComponent = e.getComponent();
                if (clickedComponent instanceof Tile) {
                    Tile clickedTile = (Tile) clickedComponent;
                    tileClicked(clickedTile);
                }
            }
        });
    }

      /**
     * Initializes the board by setting up tiles and their colors, and initializes pieces.
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
     * Retrieves the tile at the specified row and column within the current layer.
     *
     * @param row    The row index of the tile
     * @param column The column index of the tile
     * @return The tile at the specified location, or null if the indices are out of bounds
     */
    @Override
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
     * Handles actions to be taken when a tile is clicked, including selecting a piece,
     * moving a piece, and changing player turns, with consideration for the 3D context.
     *
     * @param clickedTile The tile that was clicked
     */
    @Override
    public void tileClicked(Tile clickedTile) {
        // Determine the layer of the clicked tile.
        int clickedLayer = getLayerOfTile(clickedTile);

        //debugging message
        System.out.println("clickedLayer: " + clickedLayer + " currentLayer: " + currentLayer);

        // Check if the clicked layer is the active layer.
        if (clickedLayer != currentLayer) {
            // If it's not, inform the user that they can't interact with this layer.
            JOptionPane.showMessageDialog(this, "You can't move pieces in an inactive layer.",
                    "Invalid Layer Interaction", JOptionPane.ERROR_MESSAGE);
            return;
        }
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
            //debugging message
            System.out.println("Attempting to move piece from row: " + selectedTile.getRow() + ", column: " + selectedTile.getColumn() + " to row: " + clickedTile.getRow() + ", column: " + clickedTile.getColumn());
            // Deselect
            selectedTile = null;
            // Reset all tiles to their original color
            resetTiles();
        }
    }

     /**
     * Moves a piece from one tile to another within or across layers, if the move is valid.
     *
     * @param fromTile The tile from which the piece is being moved
     * @param toTile   The tile to which the piece is being moved
     */
    @Override
    public void movePiece(Tile fromTile, Tile toTile) {
        Piece piece = fromTile.getPiece();
        if (piece != null) {
            int fromLayer = getLayerOfTile(fromTile);
            int toLayer = getLayerOfTile(toTile);
            System.out.println("fromLayer: " + fromLayer + " toLayer: " + toLayer);
            boolean isValidLayerMove = piece.canMoveToLayer(this, fromTile, toTile, fromLayer, toLayer);

            // if the piece can move to a different layer
            if (fromLayer != toLayer) {
                if (isValidLayerMove) {

                    layers[toLayer].getTile(toTile.getRow(), toTile.getColumn()).setPiece(piece);
                    fromTile.setPiece(null);
                    changePlayerTurn();
                } else {

                    JOptionPane.showMessageDialog(this, "You cannot move this piece to a different layer.",
                            "Invalid Layer Move", JOptionPane.ERROR_MESSAGE);
                }
            }

            else if (piece.isValidMove(this, fromTile, toTile)) {
                toTile.setPiece(piece);
                fromTile.setPiece(null);
                changePlayerTurn();
            } else {

                JOptionPane.showMessageDialog(this, "Invalid move", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

     /**
     * Retrieves the Board2D layer at the specified index.
     *
     * @param layerIndex The index of the layer to retrieve
     * @return The Board2D layer at the specified index, or null if the index is out of bounds
     */
    public Board2D getLayer(int layerIndex) {
        if (layerIndex >= 0 && layerIndex < layers.length) {
            return layers[layerIndex];
        } else {
            return null;
        }
    }

     /**
     * Determines the layer index of a given tile.
     *
     * @param tile The tile for which to find the layer index
     * @return The index of the layer that contains the tile, or -1 if the tile is not found in any layer
     */
    public int getLayerOfTile(Tile tile) {
        for (int layer = 0; layer < layers.length; layer++) {
            if (Arrays.asList(layers[layer].getComponents()).contains(tile)) {
                return layer;
            }
        }
        return -1; // Tile not found in any layer
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
     * Places pieces on the board in their initial positions, specific to a 3D board.
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
     * @param selectedTile The tile that should not be reset
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

     /**
     * Changes the current active layer to a new layer, and updates the layer borders accordingly.
     *
     * @param newLayer The index of the new layer to be made active
     */
    public void changeCurrentLayer(int newLayer) {
        if (newLayer >= 0 && newLayer < layers.length) {
            currentLayer = newLayer;
            for (int i = 0; i < layers.length; i++) {
                if (i == currentLayer) {
                    layers[i].setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    // Enable the board, but the actual logic will check if the layer is active
                    layers[i].setEnabled(true); 
                } else {
                    layers[i].setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                    // The board is enabled, but clicks are ignored if it's not the active layer
                    layers[i].setEnabled(true); 
                }
            }
        }
    }
}
