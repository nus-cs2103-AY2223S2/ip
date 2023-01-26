package presentation.ui;

import domain.entities.core.Writable;

public class SystemErr implements Writable {
    @Override
    public void write(Object content) {
        System.err.print(content.toString());
    }

    @Override
    public void writeln(Object content) {
        System.err.println(content.toString());
    }
}
