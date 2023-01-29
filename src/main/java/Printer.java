public class Printer {
    private static String bar = "____________________________________________________________";
    private static String indent = "    ";
    public static void printBar() {
        System.out.println(indent + bar);
    }
    public static void printText(String text) {
        System.out.println(indent + text);
    }
    public static void printException(SundayException e) {
        printBar();
        printText(e.getMessage());
        printBar();
    }
}
