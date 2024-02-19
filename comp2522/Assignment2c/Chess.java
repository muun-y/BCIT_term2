import javax.swing.*;
import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * COMP2522 Assignment2c
 * The Chess class initializes and runs the chess game application.
 * This class is responsible for setting up the main window frame of the game
 * and launching the Swing application using the event dispatch thread.
 * 
 * @author Mun Young Cho
 */
public class Chess {
    /**
     * The main method is the entry point of the application.
     * It schedules the application to be run on the Swing event dispatch thread.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessFrame frame = new ChessFrame("Chess Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false); // Prevent resizing of the window.
            frame.setSize(800, 800); // Set the window size.
            frame.setLocationRelativeTo(null); // Center the window on the screen.
            frame.setVisible(true);
        });
    }
}

/**
 * The class ChessFrame is a subclass of JFrame that represents the main window
 * of the chess game. It holds all the components necessary to display the
 * game's user interface.
 */
class ChessFrame extends JFrame {
    public ChessFrame(String title) {
        super(title);
        // Use a layout that will place the boards side by side
        setLayout(new GridLayout(1, 3)); // 1 row, 3 columns

        // create a new board
        Board3D board3D = new Board3D();

        // create a new control panel
        board3D.getLayer(0).setBorder(BorderFactory.createLineBorder(Color.RED));
        board3D.getLayer(1).setBorder(BorderFactory.createLineBorder(Color.GREEN));
        board3D.getLayer(2).setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // Add mouse listener to each layer
        for (int i = 0; i < 3; i++) {
            final int layerIndex = i; // Capture the layerIndex in a final variable
            board3D.getLayer(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Iterate through the tiles in the layer to find the clicked tile
                    Component[] components = board3D.getLayer(layerIndex).getComponents();
                    for (Component component : components) {
                        if (component instanceof Tile) {
                            Tile tile = (Tile) component;
                            if (tile.getBounds().contains(e.getPoint())) {
                                board3D.tileClicked(tile);
                                break; // Exit the loop once the clicked tile is found
                            }
                        }
                    }
                }
            });
        }
        add(board3D.getLayer(0));
        add(board3D.getLayer(1));
        add(board3D.getLayer(2));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
