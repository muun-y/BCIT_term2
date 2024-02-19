import javax.swing.*;

/**
 * COMP2522 Assignment2b
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
 * of the chess game. It holds all the components necessary to display the game's user interface.
 */
class ChessFrame extends JFrame {
    /**
     * Constructs a new frame for the chess game.
     *
     * @param title The title of the window frame.
     */
    public ChessFrame(String title) {
        super(title);
        Board board = new Board();
        this.setContentPane(board);
        this.setResizable(false); 
        this.setSize(800, 800); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null); 
    }
}
