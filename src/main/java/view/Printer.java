package view;

public class Printer implements Printable {
    private static final String SEPARATOR = "====================";

    public static void listPrint(String... args) {
        for (int i = 1; i <= args.length; ++i) {
            System.out.printf("\t%d. %s\n", i, args[i - 1]);
        }
        System.out.println(SEPARATOR);
    }

    @Override
    public void println(String out) {
        System.out.printf(out + "\n%s\n", SEPARATOR);
    }

    @Override
    public void printlnIndent(String out) {
        System.out.printf("\t%s\n%s\n", out, SEPARATOR);
    }

    @Override
    public void printIndent(String out) {
        System.out.printf("\t%s\n", out);
    }

    @Override
    public void printlnError(String out) {
        System.out.printf("\t*Error* %s\n%s\n", out, SEPARATOR);
    }
}
