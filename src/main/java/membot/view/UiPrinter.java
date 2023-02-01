package membot.view;

import java.util.function.Consumer;

public class UiPrinter implements Printable {
    private final Consumer<String> printer;

    public UiPrinter(Consumer<String> printer) {
        this.printer = printer;
    }

    @Override
    public void println(String... out) {
        if (out.length == 0) {
            return;
        }

        printer.accept(concat(out));
    }

    @Override
    public void printlnError(String... out) {
        if (out.length == 0) {
            return;
        }

        printer.accept(String.format("*Error*\n%s", concat(out)));
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
