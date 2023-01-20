package presentation.ui;

import domain.entities.core.Writable;

/**
 * This will be writing the contents to the System's output stream.
 */
public class SystemOut implements Writable {
    @Override
    public void write(Object content) {
        System.out.print(content.toString());
    }

    @Override
    public void writeln(Object content) {
        System.out.println(content.toString());
    }
}
