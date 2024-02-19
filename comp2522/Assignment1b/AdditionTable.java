/**
 * COMP2522 Assignment1b. 
 * This class represents an AdditionTable that extends the Table class.
 * It calculates and stores addition values in the table.
 * @author Mun Young Cho(A01330048)
 */
public class AdditionTable extends Table {
    /**
     * Constructor for AdditionTable.
     * Initializes the table and calculates the addition values.
     * @param start The starting value of the table.
     * @param stop The stopping value of the table.
     */
    public AdditionTable(int start, int stop) {
        super(start, stop);
        super.tableType = TableType.ADDITION; 
        calculateTable();
    }

    /**
     * Private method to calculate and populate the addition values in the table.
     */
    private void calculateTable() {
        for (int i = start; i <= stop; i++) {
            for (int j = start; j <= stop; j++) {
                values[i - start][j - start] = i + j;
            }
        }
    }
}
