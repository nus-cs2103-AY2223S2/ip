package chungus;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;

/**
 * A simple console based UI.
 */
class Ui {
    private Scanner in;
    private Writer out;

    /**
     * Constructor for a Ui. By default, stdin and stdout are used for I/O.
     */
    public Ui() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    /**
     * Another constructor for Ui.
     * 
     * @param in  Some input.
     * @param out Some output.
     */
    public Ui(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out);
    }

    /**
     * Gets the next line from input. The line is guaranteed to be trimmed of
     * whitespaces.
     * 
     * @return The next line.
     */
    public String nextLine() {
        return in.nextLine().trim();
    }

    /**
     * Prints something to the output with no special formatting.
     * 
     * @param msg  A format string.
     * @param args Arguments for the format string.
     * @throws RuntimeException If some I/O exception occurs.
     */
    public void print(String msg, Object... args) {
        try {
            out.append(String.format(msg, args)).flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays something and formats it as "information".
     * 
     * @param msg  A format string.
     * @param args Arguments for the format string.
     * @throws RuntimeException If some I/O exception occurs.
     */
    public void info(String msg, Object... args) {
        try {
            out.append("\u001b[36m")
                    .append(String.format(msg, args))
                    .append('\n')
                    .append("\u001b[0m")
                    .flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays something and formats it as an error.
     * 
     * @param msg  A format string.
     * @param args Arguments for the format string.
     * @throws RuntimeException If some I/O exception occurs.
     */
    public void error(String msg, Object... args) {
        try {
            out.append("\u001b[31m")
                    .append(String.format(msg, args))
                    .append('\n')
                    .append("\u001b[0m")
                    .flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
