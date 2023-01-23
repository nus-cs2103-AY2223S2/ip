package duke;

public class Ui {
    static final String LINES = "\t____________________________________________________________";

    public Ui() {
    }

    public static void line(boolean hasNextLine) {
        if (hasNextLine) {
            System.out.println(LINES);
        } else {
            System.out.print(LINES);
        }
    }

    public static void print(String s) {
        line(true);
        System.out.println("\t" + s);
        line(true);
    }

    public static void greet() {
        print("Hello! I'm Duke\n" +
                "\tWhat can I do for you?");
    }

    public static void goodbye() {
        print("Bye. Hope to see you again soon!");
    }

}
