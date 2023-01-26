package presentation.ui;

import domain.entities.core.Writable;

/**
 * The {@link Writable} wrapper for the {@link System#err} object.
 */
public class SystemErr implements Writable {
    @Override
    public void write(Object content) {
        System.err.print(content.toString());
    }

    @Override
    public void clear() {
        System.err.print("\033[H\033[2J");
        System.err.flush();
    }

    @Override
    public void writeln(Object content) {
        System.err.println(content.toString());
    }
}
