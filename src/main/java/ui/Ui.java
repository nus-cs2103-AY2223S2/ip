package ui;

public class Ui {
    private static final String logo = "     ____        _\n"
            + "    |  _ \\ _   _| | _____\n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    private static final String greet = "    ____________________________________________________________\n"+
            "    Hello! I'm Duke\n" +
            "    What can I do for you?\n" +
            "    ____________________________________________________________";

    private static final String line = "    ____________________________________________________________\n";

    public static void display() {
        System.out.println("    Hello from\n" + logo);
        System.out.println(greet);
    }

    public static String wrapLines(String message) {
        return "    ____________________________________________________________\n" +
                "    " + message + "\n" +
                "    ____________________________________________________________";
    }

    public static String line() {
        return line;
    }
}
