package meggy;

import java.io.Closeable;
import java.io.OutputStream;
import java.io.PrintStream;

/** The text-based UI used by the chatbot. */
public class UI implements Closeable {
    private final PrintStream out;

    /** @param out Non-null. The customizable output stream. */
    public UI(OutputStream out) {
        this.out = out instanceof PrintStream ? (PrintStream) out : new PrintStream(out);
    }

    /** Displays {@code String.valueOf(obj)} on UI. */
    public void disp(Object obj) {
        out.print(obj);
    }

    /** Displays {@code String.valueOf(obj)} on UI and creates a new line. */
    public void dispLn(Object obj) {
        out.println(obj);
    }

    /** Prevents possible resource leakage. Call after all display work is done. */
    @Override
    public void close() {
        out.close();
    }
}
