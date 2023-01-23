package view;

/**
 * Represents a <code>Printable</code> object that outputs data to stdout.
 */
public class Printer implements Printable {
    private static final String SEPARATOR = "====================";

    /**
     * Prints an array of data to <code>stdout</code>. Data is prefixxed with
     * running index starting from 1. For example:
     * 1. Data 1
     * 2. Data 2
     * ...
     *
     * @param args Array of data to be printed out.
     */
    public static void listPrint(String... args) {
        for (int i = 1; i <= args.length; ++i) {
            System.out.printf("\t%d. %s\n", i, args[i - 1]);
        }
        System.out.println(SEPARATOR);
    }

    /**
     * Prints data to <code>stdout</code> followed by a new line.
     *
     * @param out Data to be printed.
     */
    @Override
    public void println(String out) {
        System.out.printf(out + "\n%s\n", SEPARATOR);
    }

    /**
     * Prints data to <code>stdout</code> with indent, followed by a new line.
     *
     * @param out Data to be printed.
     */
    @Override
    public void printlnIndent(String out) {
        System.out.printf("\t%s\n%s\n", out, SEPARATOR);
    }

    /**
     * Prints data to <code>stdout</code> indented.
     *
     * @param out Data to be printed.
     */
    @Override
    public void printIndent(String out) {
        System.out.printf("\t%s\n", out);
    }

    /**
     * Prints error message to <code>stdout</code> followed by a new line.
     *
     * @param out Data to be printed.
     */
    @Override
    public void printlnError(String out) {
        System.out.printf("\t*Error* %s\n%s\n", out, SEPARATOR);
    }
}
