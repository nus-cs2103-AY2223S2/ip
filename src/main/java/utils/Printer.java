package utils;

public class Printer {
    private static final String SEPARATOR = "====================";

    public static void println(String out) {
        System.out.printf(out + "\n%s\n", SEPARATOR);
    }

    public static void printlnIndent(String out) {
        System.out.printf("\t%s\n%s\n", out, SEPARATOR);
    }

    public static void listPrint(String... args) {
        for (int i = 1; i <= args.length; ++i) {
            System.out.printf("\t%d. %s\n", i, args[i - 1]);
        }
        System.out.println(SEPARATOR);
    }

    public static void printIndent(String out) {
        System.out.printf("\t%s\n", out);
    }

    public static void printlnError(String out) {
        System.out.printf("\t*Error* %s\n%s\n", out, SEPARATOR);
    }
}
