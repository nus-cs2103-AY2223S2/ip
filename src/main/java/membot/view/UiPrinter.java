package membot.view;

import java.util.function.Consumer;

/**
 * Printer for JavaFX view interface.
 */
public class UiPrinter implements Printable {
    private final Consumer<Message> printer;

    public UiPrinter(Consumer<Message> printer) {
        this.printer = printer;
    }

    @Override
    public void println(String... out) {
        if (out.length == 0) {
            return;
        }

        printer.accept(new Message(concat(out), false));
    }

    @Override
    public void printlnError(String... out) {
        if (out.length == 0) {
            return;
        }

        printer.accept(new Message(concat(out), true));
    }

    @Override
    public void printSeparator() {
        // Does nothing as UI does not need any separator.
    }

    private String concat(String[] in) {
        StringBuilder sb = new StringBuilder();
        for (String s : in) {
            sb.append(String.format("%s\n", s));
        }

        return sb.toString();
    }
}
