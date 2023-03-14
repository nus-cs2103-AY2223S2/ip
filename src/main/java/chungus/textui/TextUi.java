package chungus.textui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import chungus.NonBlockingUi;

/**
 * A simple console based UI.
 */
public class TextUi implements NonBlockingUi {
    private Scanner in;
    private Writer out;

    /**
     * Constructor for a Ui. By default, stdin and stdout are used for I/O.
     */
    public TextUi() {
        in = new Scanner(System.in);
        out = new PrintWriter(System.out);
    }

    /**
     * Another constructor for Ui which specifies the input and output sources.
     * 
     * @param in  Some input.
     * @param out Some output.
     */
    public TextUi(InputStream in, OutputStream out) {
        this.in = new Scanner(in);
        this.out = new PrintWriter(out);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(Consumer<String> inputHandler, Runnable before, Runnable after, AtomicBoolean isRunning) {
        new Thread(() -> {
            while (isRunning.get()) {
                before.run();
                inputHandler.accept(in.nextLine().trim());
                after.run();
            }
        }).start();
    }

    /**
     * Prints something to the output with no special formatting.
     * 
     * @param msg  A format string.
     * @param args Arguments for the format string.
     * @throws RuntimeException If some I/O exception occurs.
     */
    @Override
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
    @Override
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
    @Override
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
