/**
 * COMP2522 Assignment1b. 
 * The Table class represents a table of integer values with specified start and stop values.
 * It can display the table with either addition or multiplication signs.
 * @author Mun Young Cho(A01330048)
 */
public class Table {
    protected int start; 
    protected int stop; 
    protected int[][] values; 
    protected TableType tableType; 

    /**
     * Constructor to initialize the Table object with a specified start and stop value.
     * @param start The starting value of the table.
     * @param stop The stopping value of the table.
     */
    protected Table(int start, int stop) {
        this.start = start; 
        this.stop = stop; 
        this.values = new int[stop - start + 1][stop - start + 1]; 
        this.tableType = tableType; 
    }
    
    /**
     * Method to display the table, taking a TableType as a parameter.
     * @param tableType The type of table to display (ADDITION or MULTIPLICATION).
     */
    public void display() {
        
        if (tableType.equals(tableType.ADDITION)) {
            System.out.printf("%5s", "+");
        } else {
            System.out.printf("%5s", "*");
        }
        
        for (int i = start; i <= stop; i++) {
            System.out.printf("%5s", i);
        }

        System.out.println(""); 

        for (int i = 0; i < stop - start + 1; i++) {
            if (i == 0) {
                System.out.printf("%5s", "");
            }
            System.out.printf("%s", "-----");
        }
        
        for (int i = 0; i < values.length; i++) {
            System.out.printf("%n%3d%s", i + start, " |");
            for (int j = 0; j < values[i].length; j++) {
                System.out.printf("%5d", (int) values[i][j]);
            }
        }
        System.out.println(); 
    }
}
