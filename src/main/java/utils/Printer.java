package utils;

public class Printer {
    private static final String SEPARATOR = "====================";

    public static void println(String out) {
        System.out.printf(out + "\n%s\n", SEPARATOR);
    }

    public static void printlnIndent(String out) {
        System.out.printf("\t%s\n%s\n", out, SEPARATOR);
    }
}
