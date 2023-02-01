package membot.view;

import java.util.function.Consumer;

public class UiPrinter implements Printable {
    private final Consumer<String> printer;

    public UiPrinter(Consumer<String> printer) {
        this.printer = printer;
    }

    @Override
    public void listPrint(String... args) {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= args.length; ++i) {
            s.append(String.format("%d. %s\n", i, args[i - 1]));
        }
        this.printer.accept(s.toString());
    }

    @Override
    public void println(String out) {
        if (out.isEmpty()) {
            return;
        }

        printer.accept(out);
    }

    @Override
    public void printlnIndent(String out) {
        if (out.isEmpty()) {
            return;
        }

        printer.accept(out);
    }

    @Override
    public void printlnError(String out) {
        if (out.isEmpty()) {
            return;
        }

        printer.accept(out);
    }

    @Override
    public void printIndent(String out) {
        if (out.isEmpty()) {
            return;
        }

        printer.accept(out);
    }
}
