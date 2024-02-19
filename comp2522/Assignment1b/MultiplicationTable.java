/**
 * COMP2522 Assignment1b. 
 * This class represents a MultiplicationTable that extends the Table class.
 * It calculates and stores multiplication values in the table.
 * @author Mun Young Cho(A01330048)
 */
public class MultiplicationTable extends Table {
    /**
     * Constructor for MultiplicationTable.
     * Initializes the table and calculates the multiplication values.
     * @param start The starting value of the table.
     * @param stop The stopping value of the table.
     */
    public MultiplicationTable(int start, int stop) {
        super(start, stop);
        super.tableType = TableType.MULTIPLICATION; 
        calculateTable();
    }

    /**
     * Private method to calculate and populate the multiplication values in the table.
     */
    private void calculateTable() {
        for (int i = start; i <= stop; i++) {
            for (int j = start; j <= stop; j++) {
                values[i - start][j - start] = i * j;
            }
        }
    }
}
