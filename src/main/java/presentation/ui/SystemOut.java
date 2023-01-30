package presentation.ui;

import domain.entities.core.Writable;

/**
 * The {@link Writable} wrapper for the {@link System#out} object.
 */
public class SystemOut implements Writable {
    @Override
    public void write(Object content) {
        System.out.print(content.toString());
    }

    @Override
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    @Override
    public void writeln(Object content) {
        System.out.println(content.toString());
    }
}
