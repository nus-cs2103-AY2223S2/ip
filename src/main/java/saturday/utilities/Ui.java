package saturday.utilities;

public class Ui {
    public static void divider() {
        System.out.println("\t_______________________________________________________");
    }
    public static void newline() {
        System.out.println();
    }
    public static void output(String text) {
        System.out.println("\t" + text);
    }
    public static void greet() {
        Ui.divider();
        Ui.output("Hello! I'm saturday.Saturday\n\tWhat can I do for you?");
        Ui.divider();
        Ui.newline();
    }
}
