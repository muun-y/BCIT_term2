/**
 * COMP2522 Lab1.
 * This class generates an arithmetic table based on user input.
 * The table can be either an addition or multiplication table.
 * It takes three command-line arguments: type, start, and stop.
 * Type can be either '+' (addition) or '*' (multiplication).
 * Start and stop specify the range of numbers for the table.
 * The table is then created and printed to the console.
 *
 * @author Mun Young Cho(A01330048)
 */

public class ArithmeticTable {

    /**
     * Declare the variable start as an integer type.
     */
    int start;

    /**
     * Declare the variable end as an integer type.
     */
    int end;

    /**
     * Declare the object tableType.
     */
    TableType tableType;

    /**
     * Declare the TableType as an enum with two values: MULT and ADD.
     */
    private enum TableType {
        MULT, ADD
    }

    float table[][];

    /**
     * Create the arithmetic table based on user input.
     *
     * @param begin     The starting value for the table.
     * @param finish    The ending value for the table.
     * @param tableType The type of table to create (MULT or ADD).
     */
    public void createTable(int begin, int finish, TableType tableType) {
        table = new float[end - start + 1][end - start + 1];
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (tableType == TableType.ADD) {
                    table[i][j] = (i + start) + (j + start);
                } else {
                    table[i][j] = (i + start) * (j + start);
                }
            }
        }
    }

    /**
     * Method to print the generated arithmetic table to the console.
     */
    public void printTable() {
        if (tableType.equals(TableType.ADD)) {
            System.out.printf("%5s", "+");
        } else {
            System.out.printf("%5s", "*");
        }

        for (int i = start; i <= end; i++) {
            System.out.printf("%5s", i);
        }

        System.out.println("");

        for (int i = 0; i < end - start + 1; i++) {
            if (i == 0) {
                System.out.printf("%5s", "");
            }
            System.out.printf("%s", "-----");
        }
        for (int i = 0; i < table.length; i++) {
            System.out.printf("%n%3d%s", i + start, " |");
            for (int j = 0; j < table[i].length; j++) {
                System.out.printf("%5d", (int) table[i][j]);
            }
        }
    }

    
    public boolean argumentCheck(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +, \"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        if (args[0].charAt(0) == '+')
            tableType = TableType.ADD;
        else
            tableType = TableType.MULT;
        int sta;
        int sto;

        try {
            sta = Integer.parseInt(args[1]);
            sto = Integer.parseInt(args[2]);
        } catch (NumberFormatException ex) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +, \"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        if ((sta < 1 || sta > 100) || ((sto < 1 || sto > 100))) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +, \"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        if (sta >= sto) {
            System.err.println("Usage: Main <type> <start> <stop>");
            System.err.println("\tWhere <type> is one of +,\"*\"");
            System.err.println("\tand <start> is between 1 and 100");
            System.err.println("\tand <stop> is between 1 and 100");
            System.err.println("\tand start < stop");
            return false;
        }

        start = sta;
        end = sto;
        return true;
    }

    public static void main(String[] args) {
        ArithmeticTable table = new ArithmeticTable();
        if (table.argumentCheck(args)) {
            table.createTable(table.start, table.end, table.tableType);
            table.printTable();
        }
    }
}
