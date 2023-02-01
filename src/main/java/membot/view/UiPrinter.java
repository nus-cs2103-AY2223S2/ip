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
    public void println(boolean isIndent, String... out) {
        // isIndent is ignored
        if (out.length == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (String s : out) {
            sb.append(String.format("%s\n", s));
        }

        printer.accept(sb.toString());
    }

    @Override
    public void printlnError(String out) {
        if (out.isEmpty()) {
            return;
        }

        printer.accept(out);
    }

    @Override
    public void printSeparator() {

    }
}
